package com.edu.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class LoginController {
	
	@GetMapping("login")
	String getLoginForm() {
		return "loginForm";
	}
	
	@GetMapping("welcome")
	String getWelcome() {
		return "You have got an access to API routes";
	}

}

