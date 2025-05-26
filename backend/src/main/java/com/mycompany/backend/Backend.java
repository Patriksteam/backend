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
            if (userRepository.findByEmail("Patrik").isEmpty()) {
                User admin = new User();
                admin.setUsername("Admin");
                admin.setEmail("admin@example.com");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRoles("ADMIN");
                // Falls du noch weitere Pflichtfelder hast, hier setzen
                userRepository.save(admin);
                System.out.println("Admin user created: admin@example.com / admin123");
            }
        };
    }
}
