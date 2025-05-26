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
            .requestMatchers(
                "/login", "/userForm",
                "/home", "/partner", "/support", "/ueber_uns",
                "/*.css", "/*.js", "/*.png", "/*.jpg",
                "/*.woff2", "/*.svg"
            ).permitAll()
            .anyRequest().access(adminOverridesEverything())
        )
        .formLogin(form -> form
            .loginPage("/login")
            .usernameParameter("email")
            .passwordParameter("password")
            .defaultSuccessUrl("/profil", true)
            .permitAll()
        )
        .rememberMe(remember -> remember
            .key("ein_geheimer_key_12345") // unbedingt anpassen!
            .tokenValiditySeconds(7 * 24 * 60 * 60) // 7 Tage
            .rememberMeParameter("remember-me")
        )
        .logout(logout -> logout
            .logoutUrl("/logout")
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID", "remember-me")
            .logoutSuccessUrl("/")
            .permitAll()
        );

    return http.build();
}


    // Zugriffskontrolle: Admins dürfen alles, andere nur wenn authentifiziert
    private AuthorizationManager<RequestAuthorizationContext> adminOverridesEverything() {
        return (authenticationSupplier, context) -> {
            Authentication auth = authenticationSupplier.get();

            boolean isAdmin = auth != null && auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

            if (isAdmin) {
                return new AuthorizationDecision(true); // Admins dürfen alles
            }

            boolean isAuthenticated = auth != null && auth.isAuthenticated();
            return new AuthorizationDecision(isAuthenticated); // andere nur, wenn eingeloggt
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}



