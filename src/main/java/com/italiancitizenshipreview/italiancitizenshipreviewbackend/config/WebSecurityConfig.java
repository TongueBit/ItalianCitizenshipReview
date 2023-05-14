package com.italiancitizenshipreview.italiancitizenshipreviewbackend.config;

import com.italiancitizenshipreview.italiancitizenshipreviewbackend.service.JpaUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    private final JpaUserDetailsService jpaUserDetailsService;

    public WebSecurityConfig(JpaUserDetailsService jpaUserDetailsService) {
        this.jpaUserDetailsService = jpaUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http

                .authorizeRequests(auth -> auth
                        .requestMatchers("/src/main/resources/static/css/home.css",
                                "/home"
                                ,"/"
                                ,"/user/dashboard/**"
                                , "/register"
                                , "/login"
                                , "/logout"
                                , "/error"
                                , "/js/**"
                                , "/rest/user/exists/**"
                                , "/rest/service-provider"
                                , "/resources/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .permitAll()
                .and()
                .userDetailsService(jpaUserDetailsService)
                .headers(headers -> headers.frameOptions().sameOrigin())
                .csrf().disable()
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



}