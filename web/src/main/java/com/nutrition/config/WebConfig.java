package com.nutrition.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author a.shestovsky
 */

@Configuration
@ComponentScan(basePackages = {"com.nutrition.controller"})
@EnableWebMvc
@Import(ThymeleafConfig.class)
public class WebConfig {

}
