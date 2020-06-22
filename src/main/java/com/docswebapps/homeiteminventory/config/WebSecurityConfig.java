package com.docswebapps.homeiteminventory.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String API_LOCATION_URL_V1="/api/v1/location";
    private static final String WEB_LOCATION_URL="/web/location";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable(); // Required to use H2 in memory DB - remove for Prod
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/img/**").permitAll()
                .antMatchers(API_LOCATION_URL_V1+"/**").permitAll()
                .antMatchers(WEB_LOCATION_URL+"/**").permitAll()
                .anyRequest().authenticated()
                .and().cors()
                .and().csrf().disable();
    }
}

