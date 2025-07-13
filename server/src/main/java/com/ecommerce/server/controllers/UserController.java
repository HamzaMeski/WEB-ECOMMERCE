package com.ecommerce.server.controllers;

import com.ecommerce.server.dtos.user.UserRequestDTO;
import com.ecommerce.server.dtos.user.UserResponseDTO;
import com.ecommerce.server.security.CurrentUser;
import com.ecommerce.server.security.UserPrincipal;
import com.ecommerce.server.services.AuthenticationService;
import com.ecommerce.server.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final AuthenticationService authService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(
            @Valid @RequestBody UserRequestDTO request
    ) {
        userService.register(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/profile")
    public ResponseEntity<UserResponseDTO> getProfile(
            @CurrentUser UserPrincipal authUser
    ) {
        return ResponseEntity.ok(userService.getAuthenticatedUser(authUser.getId()));
    }
}
