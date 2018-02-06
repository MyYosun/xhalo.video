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
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import static net.xhalo.video.config.FilePathConfig.IMAGE_SAVE_PATH;
import static net.xhalo.video.config.FilePathConfig.VIDEO_SAVE_PATH;

@Controller
public class VideoController {
    @Autowired
    private IVideoService videoService;

    /**
     *
     * @param upload
     * @param video
     * @param errors
     * @param request
     * @return
     * 上传文件和表单时注意接受表单的对象不要加注解
     */
    //TODO:上传的表单数据接受有问题
    @RequestMapping(value = "uploadVideo", method = RequestMethod.POST)
    public String addVideo(@RequestPart MultipartFile upload, @Valid Video video,
                           Errors errors, HttpServletRequest request) {
        if(null == upload || upload.isEmpty() || errors.hasErrors())
            return "error";
        if(videoService.addVideo(upload, video, request) != null)
            return "index";
        else
            return "error";
    }

    @RequestMapping(value = "getNewVideos")
    @ResponseBody
    public List<Video> getNewVideos(HttpServletRequest request) {
        List<Video> newVideoList = null;
        newVideoList = videoService.getNewVideos(request, 1, 3);
        return newVideoList;
    }

    @RequestMapping(value = "getRecommandVideosByCategoryAndPage")
    @ResponseBody
    public List<Video> getRecommandVideosByCategoryAndPage(@RequestBody Video video) {
        return null;
    }
}
