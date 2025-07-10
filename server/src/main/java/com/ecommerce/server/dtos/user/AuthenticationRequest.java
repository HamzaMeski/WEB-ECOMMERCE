package com.ecommerce.server.dtos.user;
import lombok.Getter;

@Getter
public class AuthenticationRequest {
    private String email;
    private String password;
}
