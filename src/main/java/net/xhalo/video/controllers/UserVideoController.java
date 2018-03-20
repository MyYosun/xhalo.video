package net.xhalo.video.controllers;

import net.xhalo.video.model.Comment;
import net.xhalo.video.model.Video;
import net.xhalo.video.service.IUserVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
@Scope("prototype")
public class UserVideoController {

    @Autowired
    private IUserVideoService userVideoService;

    @RequestMapping(value = "/userAddLikeVideo")
    @ResponseBody
    public String userAddLikeVideo(Video video) {
        if (userVideoService.addLoginUserLikeVideo(video)) {
            return "addSuccess";
        }
        return "addFail";
    }

    @RequestMapping(value = "/userDeleteLikeVideo")
    @ResponseBody
    public String userDeleteLikeVideo(Video video) {
        if (userVideoService.deleteLoginUserLikeVideo(video)) {
            return "deleteSuccess";
        }
        return "deleteFail";
    }

    @RequestMapping(value = "/validateUserLikeVideo")
    @ResponseBody
    public String validateUserLikeVideo(Video video) {
        if (userVideoService.validateUserLikeVideo(video)) {
            return "like";
        }
        return "unlike";
    }

    @RequestMapping(value = "/userAddVideoComment")
    @ResponseBody
    public String addVideoComment(@Valid Comment comment, Errors errors) {
        if (errors.hasErrors()) {
            return "addFail";
        }
        if (userVideoService.userAddVideoComment(comment)) {
            return "addSuccess";
        }
        return "addFail";
    }

    @RequestMapping(value = "/getVideoCommentByVideo")
    @ResponseBody
    public List<Comment> getVideoComment(Video video,
                                         @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                         @RequestParam(required = false, defaultValue = "6") Integer pageSize) {
        return userVideoService.getVideoCommentByVideo(video, pageSize, pageNum);
    }
}
