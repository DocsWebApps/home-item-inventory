package com.docswebapps.homeiteminventory.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class
WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String API_LOCATION_URL_V1="/api/v1";
    private static final String WEB_LOCATION_URL="/web";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable(); // Required to use H2 in memory DB - remove for Prod
        http.authorizeRequests()
//                .antMatchers(HttpMethod.GET,"/").permitAll()
                .antMatchers(HttpMethod.GET,"/webjars/**").permitAll()
                .antMatchers(HttpMethod.GET,"/css/**").permitAll()
                .antMatchers(HttpMethod.GET,"/img/**").permitAll()
                .antMatchers(API_LOCATION_URL_V1+"/location/**").permitAll()
                .antMatchers(WEB_LOCATION_URL+"/location").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic() // Apply HttpBasic Security
                .and().formLogin() // Provide Login Page
                .and().cors()
                .and().csrf().disable();
    }

//    // ************ Set up users for in memory authentication
//
//    // Method1: Fluent API
//    // {noop} - used to not encrypt password ie: .password("{noop}admin")
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//            .withUser("admin")
//            .password("{noop}admin")
//            .roles("ADMIN")
//            .and()
//            .withUser("user")
//            .password("{noop}user")
//            .roles("USER");
//    }
//
////    // Method2: Configure the UserDetailsService
//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("admin")
//                .roles("ADMIN").build();
//
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("user")
//                .roles("USER").build();
//
//        return new InMemoryUserDetailsManager(admin,user);
//    }
}

