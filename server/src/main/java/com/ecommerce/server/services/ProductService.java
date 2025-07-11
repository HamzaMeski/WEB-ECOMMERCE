package com.ecommerce.server.services;

import com.ecommerce.server.dtos.product.ProductRequestDTO;
import com.ecommerce.server.dtos.product.ProductResponseDTO;
import com.ecommerce.server.mappers.ProductMapper;
import com.ecommerce.server.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductResponseDTO create(ProductRequestDTO request) {
        return productMapper.toResponse(productRepository.save(productMapper.toEntity(request)));
    }

    public List<ProductResponseDTO> getProducts() {
       return productRepository.findAll().stream()
               .map(productMapper::toResponse)
               .toList();
    }
}
