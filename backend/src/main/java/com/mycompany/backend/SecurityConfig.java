package com.mycompany.backend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
public class SecurityConfig {

   @Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf() // CSRF bleibt aktiviert
            .and()
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/admin/**").hasRole("ADMIN")  // Admins nur für /admin/**
            .requestMatchers("/login", "/css/**", "/js/**", "/images/**").permitAll()  // öffentliche Ressourcen
            .anyRequest().authenticated()  // alle anderen URLs brauchen Authentifizierung
        )
        .formLogin(form -> form
            .loginPage("/login")
            .usernameParameter("email")
            .passwordParameter("password")
            .defaultSuccessUrl("/profil", true)
            .permitAll()
        )
        .logout(logout -> logout
            .logoutSuccessUrl("/")
            .permitAll()
        );

    return http.build();
}


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


