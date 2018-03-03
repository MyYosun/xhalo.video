package net.xhalo.video.controllers;

import com.github.pagehelper.PageHelper;
import net.xhalo.video.model.Video;
import net.xhalo.video.service.IVideoService;
import net.xhalo.video.utils.HashCodeUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Random;

import static net.xhalo.video.config.FilePathConfig.IMAGE_SAVE_PATH;
import static net.xhalo.video.config.FilePathConfig.VIDEO_SAVE_PATH;
import static net.xhalo.video.config.MaginNumberConfig.NUM_ONE;
import static net.xhalo.video.config.MaginNumberConfig.NUM_THREE;

@Controller
public class VideoController {
    @Autowired
    private IVideoService videoService;

    /**
     *
     * @param upload
     * @param video
     * @param errors
     * @return
     * 上传文件和表单时注意接受表单的对象不要加注解
     */
    @RequestMapping(value = "uploadVideo", method = RequestMethod.POST)
    public String addVideo(@RequestPart MultipartFile upload, @Valid Video video,
                           Errors errors) {
        if(null == upload || upload.isEmpty() || errors.hasErrors())
            return "error";
        if(videoService.addVideo(upload, video) != null)
            return "index";
        else
            return "error";
    }

    @RequestMapping(value = "getNewVideos")
    @ResponseBody
    public List<Video> getNewVideos(HttpServletRequest request) {
        List<Video> newVideoList = null;
        newVideoList = videoService.getNewVideos(request, NUM_ONE, NUM_THREE);
        return newVideoList;
    }

    @RequestMapping(value = "getRecommendVideosByCategoryAndPage")
    @ResponseBody
    public List<Video> getRecommendVideosByCategoryAndPage(Video video,
                                                           @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                           @RequestParam(required = false, defaultValue = "4") Integer pageSize) {
        List<Video> newVideoList = null;
        newVideoList = videoService.getRecommendVideosByCategoryAndPage(video, pageNum, pageSize);
        return newVideoList;
    }

    @RequestMapping(value = "video-{videoId}.html")
    public String getVideoById(@PathVariable(value = "videoId") Integer videoId, Model model) {
        Video result = null;
        result = videoService.getVideoById(videoId);
        if(result == null)
            return "error/404";
        model.addAttribute("video", result);
        return "showVideo";
    }

    @RequestMapping(value = "search")
    public String searchByTitle(@RequestParam(value = "title") String videoTitle, Model model) {
        Video result = new Video();
        result.setTitle(videoTitle);
        model.addAttribute("video", result);
        return "showVideos";
    }

    @RequestMapping(value = "getVideosByCategoryAndPage")
    @ResponseBody
    public List<Video> getVideoByCategory(Video video,
                                          @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                          @RequestParam(required = false, defaultValue = "8") Integer pageSize) {
        return videoService.getVideosByCategory(video, pageNum, pageSize);
    }

    @RequestMapping(value = "getVideosByTitleAndPage")
    @ResponseBody
    public List<Video> getVideosByTitle(Video video,
                                        @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                        @RequestParam(required = false, defaultValue = "8") Integer pageSize) {
        return videoService.getVideosByTitle(video, pageNum, pageSize);
    }


}
