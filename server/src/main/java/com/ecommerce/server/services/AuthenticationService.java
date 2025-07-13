package com.ecommerce.server.services;

import com.ecommerce.server.dtos.user.AuthenticationRequest;
import com.ecommerce.server.dtos.user.UserResponseDTO;
import com.ecommerce.server.entities.User;
import com.ecommerce.server.exception.AuthenticationException;
import com.ecommerce.server.repositories.UserRepository;
import com.ecommerce.server.security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public UserResponseDTO authenticate(AuthenticationRequest request) {

        // Try to authenticate
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (Exception e) {
            throw new AuthenticationException("Invalid email or password") {
            };
        }

        // Find user in appropriate repository
        return userRepository.findByEmail(request.getEmail())
                .map(user -> {
                    return buildAuthResponse(user);
                })
                .orElseThrow(() -> new AuthenticationException("User not found"));
    }

    private UserResponseDTO buildAuthResponse(User user) {
        String token = jwtService.generateToken(user.getEmail(), user.getId());
        return UserResponseDTO.builder()
                .token(token)
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .build();
    }
}