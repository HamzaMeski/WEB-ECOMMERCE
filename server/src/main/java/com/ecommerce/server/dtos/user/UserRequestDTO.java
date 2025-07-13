package com.ecommerce.server.dtos.user;

import lombok.Getter;

@Getter
public class UserRequestDTO {
    private String email;
    private String fullName;
    private String password;
}
