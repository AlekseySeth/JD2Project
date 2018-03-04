package com.nutrition.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author a.shestovsky
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                    .antMatchers("/my-account", "/cart")
                        .authenticated()
                    .antMatchers("/admin")
                        .hasAuthority("ADMIN")
                    .antMatchers("/marketer")
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

        http.userDetailsService(userDetailsService);
    }
}
