package com.sc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
	
@Controller
@RequestMapping("/")
public class HomeController {

	@RequestMapping//进入登录页面
	public String index(Model model) {
		return "user/login";
	}
	
	@RequestMapping("/wel")//进入欢迎页
	public String home(Model model) {
		return "welcome";
	}
}
