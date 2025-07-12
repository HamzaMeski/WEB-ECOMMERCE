package com.ecommerce.server.controllers;

import com.ecommerce.server.dtos.product.ProductRequestDTO;
import com.ecommerce.server.dtos.product.ProductResponseDTO;
import com.ecommerce.server.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping()
    public ResponseEntity<ProductResponseDTO> create(
            @RequestBody ProductRequestDTO requestDTO
    ) {
        return new ResponseEntity<>(productService.create(requestDTO), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponseDTO>> getProducts() {
        List<ProductResponseDTO> products = productService.getProducts();
        return ResponseEntity.ok(products);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(
            @PathVariable Long id,
            @RequestBody ProductRequestDTO requestDTO
    ) {
        ProductResponseDTO updated = productService.update(id, requestDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build(); // HTTP 204
    }
}
