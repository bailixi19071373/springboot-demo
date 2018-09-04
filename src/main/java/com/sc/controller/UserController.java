package com.sc.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sc.common.Code;
import com.sc.pojo.User;
import com.sc.service.UserServiceIn;

@Controller
@RequestMapping("/user")
public class UserController {

	@Inject
	private UserServiceIn userService;

	@RequestMapping("/login")
	public String login(User user, Model model, HttpSession session) {
		try {
			String result = userService.checkLogin(user);
			switch (result) {
			case Code.ERROR_USERNAME_NOTEXIST:
				model.addAttribute("info", "用户名不存在");
				return "user/login";
			case Code.ERROR_PASSWORD_WRONG:
				model.addAttribute("info", "密码不正确");
				return "user/login";
			default:
				session.setAttribute("username", user.getUsername());
				return "main";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("info", "系统异常");
			return "user/login";
		}
	}
}
