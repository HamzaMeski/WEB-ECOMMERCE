package com.ecommerce.server.dtos.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponseDTO {
    private String token;
    private Long id;
    private String email;
    private String fullName;
}
