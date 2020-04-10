package com.edu.security.security;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CorsConfig implements WebMvcConfigurer{
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedHeaders("*")
		.allowedMethods("*")
		.allowedOrigins("http://localhost:4200")
		.exposedHeaders("Authorixzation");
	}
}
