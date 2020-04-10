package com.edu.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

	@PostMapping("auth")
	String getToken() {
		return "login";
	}
	
	@GetMapping("welcome2")
	String getWelcome() {
		return "welcome";
	}
}
