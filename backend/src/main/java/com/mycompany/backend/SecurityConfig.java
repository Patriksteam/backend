package com.mycompany.backend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().and()
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/userForm", 
                        "/home", "/partner", "/support", "/ueber_uns",
                        "/*.css", "/*.js", "/*.png", "/*.jpg",
                        "/*.woff2", "/*.svg").permitAll()

                .anyRequest().access(adminOrAuthenticated())
            )
            .formLogin(form -> form
                .loginPage("/login")
                .usernameParameter("email")
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

    private AuthorizationManager<RequestAuthorizationContext> adminOrAuthenticated() {
        return (authenticationSupplier, context) -> {
            Authentication authentication = authenticationSupplier.get();
            boolean isAuthenticated = authentication != null && authentication.isAuthenticated();
            boolean isAdmin = authentication != null && authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

            return new AuthorizationDecision(isAdmin || isAuthenticated);
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


