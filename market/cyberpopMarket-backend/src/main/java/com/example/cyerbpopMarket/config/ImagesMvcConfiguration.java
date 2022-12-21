package com.example.cyerbpopMarket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ImagesMvcConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //addResourceHandler是指你想在url请求的路径
        //addResourceLocations是图片存放的真实路径
///usr/local/tomcat/images
        registry.addResourceHandler("/images/**").addResourceLocations("file:/usr/local/images/").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }
}
