package com.edu.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {
	
	@GetMapping("login")
	String getLoginForm() {
		return "loginForm";
	}
	
	@GetMapping("welcome")
	String getWelcome() {
		return "welcome";
	}

}

