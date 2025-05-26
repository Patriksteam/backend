package com.mycompany.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Backend {
    public static void main(String[] args) {
        SpringApplication.run(Backend.class, args);
    }

    @Bean
    public CommandLineRunner createAdminUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByEmail("peter@gmail.com").isEmpty()) {
                User admin = new User();
                admin.setUsername("Peter");
                admin.setEmail("peter@gmail.com");
                admin.setPassword(passwordEncoder.encode("max"));
                admin.setRoles("ADMIN");
                // Falls du noch weitere Pflichtfelder hast, hier setzen
                userRepository.save(admin);
            }
        };
    }
}
