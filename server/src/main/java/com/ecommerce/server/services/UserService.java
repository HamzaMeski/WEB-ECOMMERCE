package com.ecommerce.server.services;

import com.ecommerce.server.dtos.user.UserRequestDTO;
import com.ecommerce.server.dtos.user.UserResponseDTO;
import com.ecommerce.server.entities.User;
import com.ecommerce.server.exception.ResourceNotFoundException;
import com.ecommerce.server.mappers.AuthMapper;
import com.ecommerce.server.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthMapper authMapper;

    public void register(UserRequestDTO request) {
        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userRepository.save(user);
    }

    public UserResponseDTO getAuthenticatedUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user not found with that id"));

        return authMapper.toResponse(user);
    }
}
