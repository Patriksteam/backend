import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
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
            .csrf().and()
            .authorizeHttpRequests(authorize -> authorize
                // Statische Ressourcen erlauben
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                
                // Öffentliche Seiten erlauben
                .requestMatchers("/", "/login", "/register", "/userForm", "/addUser").permitAll()
                .requestMatchers(HttpMethod.POST, "/addUser").permitAll()

                // Alle anderen Seiten brauchen Authentifizierung
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
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


