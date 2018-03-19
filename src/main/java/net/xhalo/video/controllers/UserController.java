package net.xhalo.video.controllers;

import net.xhalo.video.model.User;
import net.xhalo.video.security.utils.SecurityUserUtil;
import net.xhalo.video.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Controller
@Scope(value = "prototype")
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private SecurityUserUtil securityUserUtil;

    @RequestMapping(value = "/processRegister")
    @ResponseBody
    public String addUser(@Valid User user, Errors errors) {
        String result = "registerFail";
        if (errors.hasErrors() || StringUtils.isAllBlank(user.getUsername())) {
            return result;
        }
        if (userService.validateUsername(user)) {
            if (userService.addUser(user))
                result = "registerSuccess";
        } else
            result = "userExist";
        return result;
    }

    @RequestMapping(value = "/validateUsername")
    @ResponseBody
    public String validateUsername(User user) {
        if (userService.validateUsername(user)) {
            return "userNotExist";
        } else {
            return "userExist";
        }
    }

    @RequestMapping(value = "/getLoginUserInfo")
    @ResponseBody
    public User getLoginUser() {
        User user = securityUserUtil.getLoginCusUser();
        user.setPassword(null);
        return user;
    }

    @RequestMapping(value = "/updateLoginUserInfo")
    @ResponseBody
    public String updateLoginUserInfo(@Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            return "updateUserInfoFail";
        }
        UserDetails userDetails = securityUserUtil.getLoginSecurityUser();
        if (null == userDetails) {
            return "userNotLogin";
        }
        if (!StringUtils.equals(user.getUsername(), userDetails.getUsername())) {
            return "usernameNotMatch";
        }
        if (userService.updateUserInfoByIdAndUsername(user)) {
            return "updateUserInfoSuccess";
        }
        return "updateUserInfoFail";
    }

    @RequestMapping(value = "/updateLoginUserHeadImg", method = RequestMethod.POST)
    @ResponseBody
    public String updateLoginUserHeadImg(@Valid User user, Errors errors, MultipartFile upload) {
        if (errors.hasErrors()) {
            return "updateUserHeadImgFail";
        }
        UserDetails userDetails = securityUserUtil.getLoginSecurityUser();
        if (null == userDetails) {
            return "userNotLogin";
        }
        if (!StringUtils.equals(user.getUsername(), userDetails.getUsername())) {
            return "usernameNotMatch";
        }
        if (userService.updateUserHeadImgByIdAndUsername(user, upload)) {
            return "updateUserHeadImgSuccess";
        }
        return "updateUserHeadImgFail";
    }

    @RequestMapping(value = "/updateLoginUserPassword")
    @ResponseBody
    public String updateLoginUserPassword(@Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            return "updateUserPasswordFail";
        }
        if (!(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails)) {
            return "userNotLogin";
        }
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!StringUtils.equals(user.getUsername(), userDetails.getUsername())) {
            return "usernameNotMatch";
        }
        if (userService.updateUserPasswordByIdAndUsername(user)) {
            return "updateUserPasswordSuccess";
        }
        return "updateUserPasswordFail";
    }

    @RequestMapping(value = "/author-{username}")
    public String getAuthorInfo(@PathVariable String username, Model model) {
        User user = new User();
        user.setUsername(username);
        user = userService.getUserByUsername(user);
        model.addAttribute("author", user);
        return "authorInfo";
    }

}
