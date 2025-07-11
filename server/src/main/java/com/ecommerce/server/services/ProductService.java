package com.ecommerce.server.services;

import com.ecommerce.server.dtos.product.ProductRequestDTO;
import com.ecommerce.server.dtos.product.ProductResponseDTO;
import com.ecommerce.server.mappers.ProductMapper;
import com.ecommerce.server.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductResponseDTO create(ProductRequestDTO request) {

        return null;
    }
}
