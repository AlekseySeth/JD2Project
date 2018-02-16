package by.nutrition.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = {
        "by.nutrition.marketing",
        "by.nutrition.order",
        "by.nutrition.product",
        "by.nutrition.user",
        "by.nutrition.dao"})
@EnableTransactionManagement
public class ServiceConfig {

}
