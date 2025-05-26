package com.mycompany.backend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

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

            .anyRequest().access((Authentication authentication, RequestAuthorizationContext context) -> {
                boolean isAdmin = authentication != null &&
                        authentication.getAuthorities().stream()
                            .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
                boolean isAuthenticated = authentication != null && authentication.isAuthenticated();
                return new AuthorizationDecision(isAdmin || isAuthenticated);
            })
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
} @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


