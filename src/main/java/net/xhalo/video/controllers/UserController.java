package net.xhalo.video.controllers;

import net.xhalo.video.model.User;
import net.xhalo.video.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class UserController {
	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/processRegister")
	@ResponseBody
	public String addUser(@Valid User user, Errors errors) {
		String result = "registerFail";
		if(errors.hasErrors()) {
			return result;
		}

		if (userService.validateUsername(user)) {
			if (userService.addUser(user))
				result = "registerSuccess";
		} else
			result = "userExist";

		return result;
	}

	@RequestMapping(value = "validateUsername")
	@ResponseBody
	public String validateUsername(User user) {
		if(userService.validateUsername(user)) {
			return "userNotExist";
		} else {
			return "userExist";
		}
	}

}
