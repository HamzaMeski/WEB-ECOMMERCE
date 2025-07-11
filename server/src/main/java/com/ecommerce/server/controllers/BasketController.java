package com.ecommerce.server.controllers;

import com.ecommerce.server.dtos.basket.BasketRequestDTO;
import com.ecommerce.server.security.CurrentUser;
import com.ecommerce.server.security.UserPrincipal;
import com.ecommerce.server.services.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/baskets")
@RequiredArgsConstructor
public class BasketController {

    private final BasketService basketService;

    @PostMapping
    public ResponseEntity<Void> create(
            @RequestBody BasketRequestDTO request,
            @CurrentUser UserPrincipal authUser
            ) {
        basketService.create(request, authUser.getId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
