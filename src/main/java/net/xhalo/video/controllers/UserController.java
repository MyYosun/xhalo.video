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
	public String addUser(@RequestBody @Valid User user, Errors errors, Model model) {
		String result = "registFail";
		if(errors.hasErrors()) {
			model.addAttribute(result);
			return result;
		}

		if (userService.validateUsername(user)) {
			if (userService.addUser(user))
				result = "registSuccess";
		} else
			result = "userExist";
		model.addAttribute(result);
		return result;
	}

	@RequestMapping(value = "/validateUser")
	@ResponseBody
	public String validateUser(@RequestBody@Valid User user, Errors errors,
							 Model model, HttpServletRequest request) {
		String result = "loginFail";
		if(errors.hasErrors()) {
			model.addAttribute(result);
			return result;
		}
		HttpSession session = null;
		User loginUser = null;
		if ((loginUser = userService.validateUser(user)) != null) {
			result = "loginSuccess";
			session = request.getSession();
			session.setAttribute("user", loginUser);
		}
		model.addAttribute(result);
		return result;
	}

	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		return "index";
	}

}
