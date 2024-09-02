package com.benq.boot.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FileConfig implements WebMvcConfigurer{
	private String webPath = "/images/notice/**";
	private String realPath = "file:C:/uploadFile/notice/";
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(webPath)
		.addResourceLocations(realPath);
	}

}
