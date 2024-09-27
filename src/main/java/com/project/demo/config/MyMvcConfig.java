package com.project.demo.config;

import com.project.demo.component.LoginHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Bean
    public WebMvcConfigurer getWebMvcConfigurer() {
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/index.html").setViewName("login/index");
                registry.addViewController("/").setViewName("login/index");
                registry.addViewController("/login.html").setViewName("login/login");
                registry.addViewController("/register.html").setViewName("login/register");
                registry.addViewController("/admin/dashboard.html").setViewName("main/dashboard");
                registry.addViewController("/admin/user.html").setViewName("main/user-management");
                registry.addViewController("/admin/settings.html").setViewName("main/settings");
                registry.addViewController("/admin/reports.html").setViewName("main/reports");
                registry.addViewController("/menu.html").setViewName("webService/menu");
            }

            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                        .excludePathPatterns("/register.html", "/index.html", "/login.html" ,
                                            "/user/login","/", "/static/**", "/webjars/**",
                                            "/public/**", "/favicon.ico", "/resources/**", "/menu.html");
            }
        };
        return webMvcConfigurer;
    }
}
