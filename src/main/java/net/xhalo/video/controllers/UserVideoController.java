package net.xhalo.video.controllers;

import net.xhalo.video.model.User;
import net.xhalo.video.model.Video;
import net.xhalo.video.service.IUserVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@Scope("prototype")
public class UserVideoController {

    @Autowired
    private IUserVideoService userVideoService;

    @RequestMapping(value = "userAddLikeVideo")
    @ResponseBody
    public String userAddLikeVideo(Video video) {
        if(userVideoService.addLoginUserLikeVideo(video)) {
            return "addSuccess";
        }
        return "addFail";
    }

    @RequestMapping(value = "userDeleteLikeVideo")
    @ResponseBody
    public String userDeleteLikeVideo(Video video) {
        if(userVideoService.deleteLoginUserLikeVideo(video)) {
            return "deleteSuccess";
        }
        return "deleteFail";
    }

    @RequestMapping(value = "validateUserLikeVideo")
    @ResponseBody
    public String validateUserLikeVideo(Video video) {
        if(userVideoService.validateUserLikeVideo(video)) {
            return "like";
        }
        return "unlike";
    }
}
