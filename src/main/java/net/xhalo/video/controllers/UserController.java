package net.xhalo.video.controllers;

import net.xhalo.video.model.User;
import net.xhalo.video.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

@Controller
public class UserController {
	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/addUser")
	@ResponseBody
	public String addUser(@RequestBody @Valid User user, Errors errors) {
		String result = "registFail";
		if(errors.hasErrors()) {
			return result;
		}

		if (userService.validateUsername(user)) {
			if (userService.addUser(user))
				result = "registSuccess";
		} else
			result = "userExist";

		return result;
	}

}
