package com.ecommerce.server.configuration;

import com.ecommerce.server.entities.User;
import com.ecommerce.server.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppInit implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        String email = "hamza@mail.com";
        String rawPassword = "hamza@mail.com";

        userRepository.findByEmail(email).ifPresentOrElse(
                user -> {
                    // User exists — do nothing
                },
                () -> {
                    User user = User.builder()
                            .email(email)
                            .password(passwordEncoder.encode(rawPassword))
                            .fullName("Hamza Init")
                            .build();
                    userRepository.save(user);
                    System.out.println("User created: " + email);
                }
        );
    }
}