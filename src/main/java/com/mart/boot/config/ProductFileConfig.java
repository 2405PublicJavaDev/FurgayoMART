package com.mart.boot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ProductFileConfig  implements WebMvcConfigurer{
	
	
	private String webPath = "/images/**"; //요청할 url  /images/**
	private String realPath = "file:C:/uploadFile/notice"; // file:C:/uploadFile/notice/ 와 같음
	
	
}
