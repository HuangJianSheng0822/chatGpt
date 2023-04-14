package com.gpt.chatgpt.config;

import com.gpt.chatgpt.handler.LoginHandlerInterceptors;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
public class GptWebMvcConfig implements WebMvcConfigurer {
    //视图控制
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/register.html").setViewName("register");
        registry.addViewController("/login.html").setViewName("login");
        WebMvcConfigurer.super.addViewControllers(registry);
    }

    //静态资源
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    //拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> strings = Arrays.asList("/index");
        registry.addInterceptor(new LoginHandlerInterceptors()).addPathPatterns(strings);
        WebMvcConfigurer.super.addInterceptors(registry);
    }


}
