package com.webdemo.springbootwebdemo.config;

import com.webdemo.springbootwebdemo.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //放行登录注册
        registry.addInterceptor(loginInterceptor).excludePathPatterns("/user/login","/user/register","/article/list");

    }
}
