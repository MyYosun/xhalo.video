package net.xhalo.video.controllers;

import net.xhalo.video.model.User;
import net.xhalo.video.model.Video;
import net.xhalo.video.service.IVideoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

import static net.xhalo.video.config.MaginNumberProperties.NUM_TWO;

@Controller
@Scope(value = "prototype")
public class VideoController {
    @Autowired
    private IVideoService videoService;

    /**
     * @param upload
     * @param video
     * @param errors
     * @return 上传文件和表单时注意接受表单的对象不要加注解
     */
    @RequestMapping(value = "/uploadVideo", method = RequestMethod.POST)
    @ResponseBody
    public String addVideo(@RequestPart MultipartFile upload, @Valid Video video,
                           Errors errors) {
        if (null == upload || upload.isEmpty() || errors.hasErrors() || StringUtils.isAllBlank(video.getTitle()))
            return "formatError";
        if (videoService.addVideo(upload, video) != null)
            return "uploadSuccess";
        else
            return "uploadFail";
    }

    @RequestMapping(value = "/getLatestVideos")
    @ResponseBody
    public List<Video> getNewVideos(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                    @RequestParam(required = false, defaultValue = "6") Integer pageSize) {
        List<Video> newVideoList = null;
        newVideoList = videoService.getNewVideos(pageNum, pageSize);
        return newVideoList;
    }

    @RequestMapping(value = "/getRecommendVideos")
    @ResponseBody
    public List<Video> getRecommendVideos(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                          @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        List<Video> newVideoList = null;
        newVideoList = videoService.getRecommendVideos(pageNum, pageSize);
        return newVideoList;
    }

    @RequestMapping(value = "/getRecommendVideosByCategoryAndPage")
    @ResponseBody
    public List<Video> getRecommendVideosByCategoryAndPage(Video video,
                                                           @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                           @RequestParam(required = false, defaultValue = "4") Integer pageSize) {
        List<Video> newVideoList = null;
        newVideoList = videoService.getRecommendVideosByCategoryAndPage(video, pageNum, pageSize);
        return newVideoList;
    }

    @RequestMapping(value = "/video-{videoId}.html")
    public String getVideoById(@PathVariable(value = "videoId") Long videoId, Model model) {
        Video result = null;
        result = videoService.getVideoById(videoId);
        if (result == null)
            return "error/404";
        model.addAttribute("video", result);
        if (StringUtils.equals(result.getCategory().getId().toString(), String.valueOf(NUM_TWO)))
            return "showVrVideo";
        return "showVideo";
    }

    @RequestMapping(value = "/search")
    public String searchByTitle(@RequestParam(value = "title") String videoTitle, Model model) {
        Video result = new Video();
        result.setTitle(videoTitle);
        model.addAttribute("video", result);
        return "showVideos";
    }

    @RequestMapping(value = "/getVideosByCategoryAndPage")
    @ResponseBody
    public List<Video> getVideoByCategory(Video video,
                                          @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                          @RequestParam(required = false, defaultValue = "9") Integer pageSize,
                                          @RequestParam(required = false, defaultValue = "all") String optionDuration,
                                          @RequestParam(required = false, defaultValue = "date") String optionOrder) {
        return videoService.getVideosByCategory(video, optionDuration, optionOrder, pageNum, pageSize);
    }

    @RequestMapping(value = "/getVideosByTitleAndPage")
    @ResponseBody
    public List<Video> getVideosByTitle(Video video,
                                        @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                        @RequestParam(required = false, defaultValue = "9") Integer pageSize,
                                        @RequestParam(required = false, defaultValue = "all") String optionDuration,
                                        @RequestParam(required = false, defaultValue = "date") String optionOrder) {
        return videoService.getVideosByTitle(video, optionDuration, optionOrder, pageNum, pageSize);
    }

    @RequestMapping(value = "/getUserUploadVideos")
    @ResponseBody
    public List<Video> getUserUploadVideos(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                           @RequestParam(required = false, defaultValue = "12") Integer pageSize) {
        return videoService.getUserUploadVideos(pageNum, pageSize);
    }

    @RequestMapping(value = "/getUploadVideosByAuthor")
    @ResponseBody
    public List<Video> getUploadVideosByAuthor(User author,
                                               @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                               @RequestParam(required = false, defaultValue = "12") Integer pageSize) {
        return videoService.getVideosByAuthor(author, pageNum, pageSize);
    }

    @RequestMapping(value = "/deleteUserUploadVideo")
    @ResponseBody
    public String deleteUserUploadVideos(Video video) {
        if (videoService.deleteUserUploadVideo(video)) {
            return "deleteSuccess";
        }
        return "deleteFail";
    }

    @RequestMapping(value = "/getUserLikeVideos")
    @ResponseBody
    public List<Video> getUserLikeVideos(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                         @RequestParam(required = false, defaultValue = "12") Integer pageSize) {
        return videoService.getUserLikeVideos(pageNum, pageSize);
    }

    @RequestMapping(value = "/deleteUserLikeVideo")
    @ResponseBody
    public String deleteUserLikeVideos(Video video) {
        if (videoService.removeUserLikeVideo(video)) {
            return "deleteSuccess";
        }
        return "deleteFail";
    }

    @RequestMapping(value = "/adminGetVideos")
    @ResponseBody
    public List<Video> getAllUploadVideos(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                          @RequestParam(required = false, defaultValue = "12") Integer pageSize) {
        return videoService.getAllVideos(pageNum, pageSize);
    }

    @RequestMapping(value = "/adminDeleteVideo")
    @ResponseBody
    public String adminDeleteVideo(Video video) {
        if (videoService.deleteById(video)) {
            return "deleteSuccess";
        }
        return "deleteFail";
    }

    @RequestMapping(value = "/adminDeleteUselessVideos")
    @ResponseBody
    public String deleteUselessVideos() {
        if (videoService.deleteUselessVideos()) {
            return "processSuccess";
        }
        return "processFail";
    }

    @RequestMapping(value = "/adminRepairUselessVideos")
    @ResponseBody
    public String repairUselessVideos() {
        if (videoService.repairUselessVideos()) {
            return "processSuccess";
        }
        return "processFail";
    }

}
