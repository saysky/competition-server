package com.xxx.project.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class MyWebMvcConfiguration implements WebMvcConfigurer {
    @Autowired
    LoginInterceptor loginHandlerInterceptor;

////    @Override
////    public void addViewControllers(ViewControllerRegistry registry) {
////        registry.addViewController("/").setViewName("forward:/login");
////        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
////    }
//static final String ORIGINS[] = new String[] { "GET", "POST", "PUT", "DELETE" };
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**").allowedOrigins("*").allowCredentials(true).allowedMethods(ORIGINS)
//                .maxAge(3600);
//    }
//    //注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截所有的请求
        registry.addInterceptor(loginHandlerInterceptor).addPathPatterns("/**").excludePathPatterns("/login").excludePathPatterns("/static/**");
    }
}
