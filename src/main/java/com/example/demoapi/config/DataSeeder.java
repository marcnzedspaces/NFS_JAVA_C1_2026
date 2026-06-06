package com.example.demoapi.config;

import com.example.demoapi.model.Role;
import com.example.demoapi.model.User;
import com.example.demoapi.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        createAdminIfMissing();
    }

    private void createAdminIfMissing() {
        String adminEmail = "admin@admin.com";

        if (userRepository.existsByEmail(adminEmail)) {
            return;
        }

        User admin = new User(
            "Admin User",
            adminEmail,
            passwordEncoder.encode("pwd12345"),
            Role.ADMIN
        );

        userRepository.save(admin);

        System.out.println("Seeded default admin user: " + adminEmail);
    }
}
