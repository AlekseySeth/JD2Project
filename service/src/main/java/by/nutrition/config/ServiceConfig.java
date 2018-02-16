package by.nutrition.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = {
        "by.nutrition.marketing",
        "by.nutrition.order",
        "by.nutrition.product",
        "by.nutrition.user"})
@Import(DatabaseConfig.class)
@EnableTransactionManagement
public class ServiceConfig {

}
