package com.nutrition.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author a.shestovsky
 */

@Configuration
@ComponentScan(basePackages = "com.nutrition.controller")
@EnableWebMvc
public class WebConfig {

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/html/");
        viewResolver.setSuffix(".html");
        viewResolver.setExposeContextBeansAsAttributes(true);
        return viewResolver;
    }
}
