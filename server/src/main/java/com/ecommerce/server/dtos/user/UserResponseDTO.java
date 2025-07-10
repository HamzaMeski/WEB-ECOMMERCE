package com.ecommerce.server.dtos.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponseDTO {
    private String token;
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
}
