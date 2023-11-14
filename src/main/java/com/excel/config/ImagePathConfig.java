package com.excel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ImagePathConfig implements WebMvcConfigurer {

	@Autowired
	private Environment env;
    
    @Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		String path = env.getProperty("image.path");

		registry.addResourceHandler("/image/dog/**").addResourceLocations(path + "dog/");

	}
}
