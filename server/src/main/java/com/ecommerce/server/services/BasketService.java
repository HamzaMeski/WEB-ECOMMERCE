package com.ecommerce.server.services;

import com.ecommerce.server.dtos.BasketProduct.BasketProductRequestDTO;
import com.ecommerce.server.dtos.basket.BasketRequestDTO;
import com.ecommerce.server.entities.Basket;
import com.ecommerce.server.entities.BasketProduct;
import com.ecommerce.server.entities.Product;
import com.ecommerce.server.entities.User;
import com.ecommerce.server.exception.ResourceNotFoundException;
import com.ecommerce.server.repositories.BasketProductRepository;
import com.ecommerce.server.repositories.BasketRepository;
import com.ecommerce.server.repositories.ProductRepository;
import com.ecommerce.server.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BasketService {
    private final UserRepository userRepository;
    private final BasketRepository basketRepository;
    private final BasketProductRepository basketProductRepository;
    private final ProductRepository productRepository;

    @Transactional
    public void create(BasketRequestDTO request, Long buyerId) {
        User buyer = userRepository.findById(buyerId)
                .orElseThrow(() -> new ResourceNotFoundException("user not found with that id"));

        Basket basket = Basket.builder()
                .user(buyer)
                .build();
        Basket createdBasket = basketRepository.save(basket);

        log.info("Products: {}",request.getProducts());

        for(BasketProductRequestDTO item : request.getProducts()) {
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("product not found with that id"));

            BasketProduct basketProduct = BasketProduct.builder()
                    .basket(createdBasket)
                    .product(product)
                    .quantity(item.getQuantity())
                    .build();

            basketProductRepository.save(basketProduct);
        }
    }
}
