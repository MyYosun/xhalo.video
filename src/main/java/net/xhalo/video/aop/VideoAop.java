package net.xhalo.video.aop;

import net.xhalo.video.model.Video;
import net.xhalo.video.utils.FFmpegUtil;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class VideoAop {

    @Pointcut("execution(* net.xhalo.video.service.IVideoService.addVideo(..))")
    public void addVideo() {}

    @AfterReturning(pointcut = "addVideo()", returning = "returnValue")
    public void videoShot(Object returnValue) {
        if(null != returnValue && returnValue instanceof Video){
            Video video = (Video) returnValue;
            String videoPath = video.getAddress();
            String imagePath = video.getView();
            FFmpegUtil.makeScreenCut(videoPath, imagePath);
        }
    }

}
