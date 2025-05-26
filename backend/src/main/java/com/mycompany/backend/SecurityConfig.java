package com.mycompany.backend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().and() // CSRF bleibt aktiviert (gut für Form-Login)
            .authorizeHttpRequests(authorize -> authorize
                // Öffentliche Endpunkte (Login, Registrierung, statische Dateien)
                .requestMatchers("/login", "/userForm", 
                        "/home","/partner","/support","/ueber_uns",
                       "/*.css", "/*.js", "/*.png", "/*.jpg",
                       "/*.woff2", "/*.svg").permitAll()

                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .usernameParameter("email") // angepasst an Formularfeld
                .passwordParameter("password")
                .defaultSuccessUrl("/profil", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
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



