package com.example.learningspring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception
    {
        http.cors()
            .and()
            .authorizeRequests()
            .anyRequest()
            .authenticated()
            .and()
            .oauth2ResourceServer()
            .jwt();

        return http.build();
    }
}
