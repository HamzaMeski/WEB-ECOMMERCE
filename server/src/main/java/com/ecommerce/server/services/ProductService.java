package com.ecommerce.server.services;

import com.ecommerce.server.dtos.product.ProductRequestDTO;
import com.ecommerce.server.dtos.product.ProductResponseDTO;
import com.ecommerce.server.entities.Product;
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

    public ProductResponseDTO update(Long id, ProductRequestDTO request) {
        return productRepository.findById(id)
                .map(existing -> {
                    Product updated = productMapper.toEntity(request);
                    updated.setId(existing.getId());
                    return productMapper.toResponse(productRepository.save(updated));
                })
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }
}
