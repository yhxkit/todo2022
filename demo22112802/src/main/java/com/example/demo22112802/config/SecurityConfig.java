package com.example.demo22112802.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // /logout 경로로 리퀘스트 받으면
                .logoutSuccessUrl("/") // 루트로 리다이렉트
                .invalidateHttpSession(true); //세션 초기화


        http.exceptionHandling().accessDeniedPage("/access_denied");

        http.authorizeRequests()
                .antMatchers("/js/**", "/css/**", "/fonts/**", "/assets/**").permitAll()
                .antMatchers("/h2console/**", "/resources/**", "/files/**").permitAll()
                .antMatchers("/", "/login", "/signup/**").permitAll()
                .antMatchers("/main", "/admin/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").and()
                .csrf().disable()
                .headers().frameOptions().disable();

        return http.build();
    }


}
