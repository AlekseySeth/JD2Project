package com.nutrition.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author a.shestovsky
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/test")
                        .authenticated()
                    .antMatchers("/test")
                        .hasAuthority("ADMIN")
                    .antMatchers("/test")
                        .hasAuthority("MARKETER")
                    .anyRequest()
                        .permitAll();
        http
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/my-account")
                .usernameParameter("email")
                .passwordParameter("password")
            .and()
                .logout()
                .logoutUrl("/logout");
    }
}
