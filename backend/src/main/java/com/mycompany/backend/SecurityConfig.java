package com.mycompany.backend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf() // CSRF ist standardmäßig aktiviert, hier zur Klarstellung
                .and()
            .authorizeHttpRequests(authorize -> authorize
                // Öffentliche Seiten & Ressourcen erlauben
                .requestMatchers(
                    "/", "/login", "/register", "/userForm", "/addUser", 
                    "/css/**", "/js/**", "/images/**", "/favicon.ico", "/static/**"
                ).permitAll()
                // POST auf /addUser explizit erlauben
                .requestMatchers(HttpMethod.POST, "/addUser").permitAll()
                // Alle anderen Requests benötigen Authentifizierung
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")       // Eigene Login-Seite (Controller muss vorhanden sein)
                .defaultSuccessUrl("/profil", true)  // Nach erfolgreichem Login
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/")  // Nach Logout auf Homepage
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

