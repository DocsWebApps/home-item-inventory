package com.docswebapps.homeiteminventory.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String LOCATION_URL_V1="/api/v1/location";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable(); // Required to use H2 in memory DB - remove for Prod
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST,LOCATION_URL_V1).permitAll()
                .antMatchers(HttpMethod.GET,LOCATION_URL_V1).permitAll()
                .antMatchers(HttpMethod.PUT,LOCATION_URL_V1+"/**").permitAll()
                .antMatchers(HttpMethod.DELETE,LOCATION_URL_V1+"/**").permitAll()
                .antMatchers(HttpMethod.GET, LOCATION_URL_V1+"/**").permitAll()
                .anyRequest().authenticated()
                .and().cors()
                .and().csrf().disable();
    }
}

