package com.nutrition.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = {
        "com.nutrition.marketing",
        "com.nutrition.order",
        "com.nutrition.product",
        "com.nutrition.user"})
@Import(DatabaseConfig.class)
@EnableTransactionManagement
public class ServiceConfig {

}
