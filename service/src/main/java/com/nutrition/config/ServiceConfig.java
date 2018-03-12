package com.nutrition.config;

import com.nutrition.aspect.Logging;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;

@Configuration
@ComponentScan(basePackages = {
        "com.nutrition.marketing",
        "com.nutrition.order",
        "com.nutrition.product",
        "com.nutrition.user"})
@Import(PersistenceConfig.class)
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class ServiceConfig {

    @Bean
    public Logger logger() {
        return Logger.getLogger("service");
    }

    @Bean
    public Logging logging() {
        return new Logging(logger());
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}
