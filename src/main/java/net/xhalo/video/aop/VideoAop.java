package net.xhalo.video.aop;

import net.xhalo.video.model.Video;
import net.xhalo.video.service.IUserVideoService;
import net.xhalo.video.service.IVideoService;
import net.xhalo.video.utils.FFmpegUtil;
import org.apache.commons.io.FileUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

import static net.xhalo.video.config.FilePathProperties.*;

@Component
@Aspect
public class VideoAop {

    @Autowired
    private IVideoService videoService;

    @Autowired
    private IUserVideoService userVideoService;

    private Logger logger = LoggerFactory.getLogger(VideoAop.class);

    @Pointcut("execution(* net.xhalo.video.service.imp.VideoServiceImp.addVideo(..))")
    public void addVideo() {
    }

    @Pointcut("execution(* net.xhalo.video.service.imp.VideoServiceImp.getVideoById(..))")
    public void getVideoById() {
    }

    @Pointcut("execution(* net.xhalo.video.service.imp.VideoServiceImp.deleteVideoByAuthorAndId(..))")
    public void deleteUserUploadVideo() {
    }

    @Pointcut("execution(* net.xhalo.video.service.imp.VideoServiceImp.deleteById(..))")
    public void deleteVideo() {
    }

    @AfterReturning(pointcut = "addVideo()", returning = "returnValue")
    public void videoShot(Object returnValue) {
        if (null != returnValue && returnValue instanceof Video) {
            Video video = (Video) returnValue;
            String videoName = video.getAddress();
            String imageName = video.getView();
            FFmpegUtil.videoCutImg(videoName, imageName);
            FFmpegUtil.videoCutImgBig(videoName, imageName);
            if (FFmpegUtil.processMediaCode(videoName)) {
                File originVideo = new File(VIDEO_SAVE_PATH + videoName);
                File targetVideo = new File(VIDEO_SAVE_PATH + videoName + "(transcode).mp4");
                if (targetVideo.exists() && targetVideo.isFile()) {
                    FileUtils.deleteQuietly(originVideo);
                    targetVideo.renameTo(originVideo);
                }
            }
        }
    }

    @AfterReturning(pointcut = "getVideoById()", returning = "returnValue")
    public void addClickById(Object returnValue) {
        if (null != returnValue && returnValue instanceof Video) {
            Video result = (Video) returnValue;
            videoService.addClickById(result.getId());
        }
    }

    @Transactional
    @Around("deleteUserUploadVideo()")
    public boolean deleteUploadVideos(ProceedingJoinPoint proceedingJoinPoint) {
        return proceedDeleteVideo(proceedingJoinPoint);
    }

    @Transactional
    @Around("deleteVideo()")
    public boolean deleteVideoProcess(ProceedingJoinPoint proceedingJoinPoint) {
        return proceedDeleteVideo(proceedingJoinPoint);
    }

    private boolean proceedDeleteVideo(ProceedingJoinPoint proceedingJoinPoint) {
        Object[] args = proceedingJoinPoint.getArgs();
        Video video = null;
        for (Object arg : args) {
            if (null != arg && arg instanceof Video) {
                video = new Video();
                Video argVideo = (Video) arg;
                if (null == argVideo.getId()) {
                    return false;
                }
                video.setId(argVideo.getId());
            }
        }
        try {
            video = videoService.getVideoByIdNotRelated(video.getId());
            if (null == video) {
                return false;
            }
            boolean result = (boolean) proceedingJoinPoint.proceed(args);
            if (result) {
                userVideoService.deleteLikeVideo(video);
                userVideoService.deleteVideoCommentByVideo(video);
                File videoFile = new File(VIDEO_SAVE_PATH + video.getAddress());
                File imageFile = new File(IMAGE_SAVE_PATH + video.getView());
                File bigImageFile = new File(BIG_IMAGE_SAVE_PATH + video.getView());
                FileUtils.deleteQuietly(videoFile);
                FileUtils.deleteQuietly(imageFile);
                FileUtils.deleteQuietly(bigImageFile);
            }
            return result;
        } catch (Throwable throwable) {
            logger.error("ERROR WHEN PROCESSING DELETE VIDEO:", throwable);
            return false;
        }
    }

}
