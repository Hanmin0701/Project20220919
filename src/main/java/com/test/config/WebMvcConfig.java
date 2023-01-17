package com.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.test.common.fileManagerService;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	// 파일 연동해주는 방법
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry
		.addResourceHandler("/images/**") // Web 이미지 주소 = // http://localhost:8080/images/aaaa_16205468768/sun.png
		.addResourceLocations("file:///" + fileManagerService.FILE_UPLOAD_PATH); // 실재 파일 위치  
		// mac = .addResourceLocations("file://");
		// window = .addResourceLocations("file:///");
	}
}
