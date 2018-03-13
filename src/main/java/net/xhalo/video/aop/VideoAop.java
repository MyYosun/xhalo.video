package net.xhalo.video.aop;

import net.xhalo.video.model.Video;
import net.xhalo.video.service.IVideoService;
import net.xhalo.video.utils.FFmpegUtil;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class VideoAop {

    @Autowired
    private IVideoService videoService;

    @Pointcut("execution(* net.xhalo.video.service.imp.VideoServiceImp.addVideo(..))")
    public void addVideo() {
    }

    @Pointcut("execution(* net.xhalo.video.service.imp.VideoServiceImp.getVideoById(..))")
    public void getVideoById() {
    }

    @AfterReturning(pointcut = "addVideo()", returning = "returnValue")
    public void videoShot(Object returnValue) {
        if (null != returnValue && returnValue instanceof Video) {
            Video video = (Video) returnValue;
            String videoName = video.getAddress();
            String imageName = video.getView();
            FFmpegUtil.videoCutImg(videoName, imageName);
            FFmpegUtil.videoCutImgBig(videoName, imageName);
            FFmpegUtil.processMediaCode(videoName);
        }
    }

    @AfterReturning(pointcut = "getVideoById()", returning = "returnValue")
    public void addClickById(Object returnValue) {
        if (null != returnValue && returnValue instanceof Video) {
            Video result = (Video) returnValue;
            videoService.addClickById(result.getId());
        }
    }

}
