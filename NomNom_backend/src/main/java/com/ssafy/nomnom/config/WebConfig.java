package com.ssafy.nomnom.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

//import com.ssafy.mvc.interceptor.AdminInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
//	@Autowired
//	AdminInterceptor adminInterceptor;
//	
//	
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(adminInterceptor).addPathPatterns("/users");
//	}
	
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**") // 모든 경로에 대해
//                .allowedOrigins("http://localhost:5173") // Vue 앱 도메인
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 필요한 메서드 허용
//                .allowedHeaders("*")
//                .allowCredentials(true);
//    }
}
