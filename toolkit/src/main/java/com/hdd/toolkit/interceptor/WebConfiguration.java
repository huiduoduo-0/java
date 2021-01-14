package com.hdd.toolkit.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer{

	@Autowired
	WebInterceptor webInterceptor;
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(webInterceptor).addPathPatterns("/**");
	}
	
	
	
	
	
	





	//
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		// TODO Auto-generated method stub
//		WebMvcConfigurer.super.addInterceptors(registry);
//	}


}