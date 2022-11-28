package com.example.demo22112802.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeRequests()
                .antMatchers("/h2console/**", "/resources/**", "/files/**").permitAll()
                .antMatchers("/auth/login").permitAll()
                .antMatchers("/test").hasRole("ADMIN")
                .anyRequest().permitAll()
                .and()
                .formLogin().and()
                .csrf().disable()
                .headers().frameOptions().disable()
                .and().build();
    }
}
