package com.ecommerce.server.services;

import com.ecommerce.server.dtos.product.ProductRequestDTO;
import com.ecommerce.server.dtos.product.ProductResponseDTO;
import com.ecommerce.server.entities.Image;
import com.ecommerce.server.entities.Product;
import com.ecommerce.server.mappers.ProductMapper;
import com.ecommerce.server.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ImageService imageService;

    public ProductResponseDTO create(ProductRequestDTO request, MultipartFile file) throws IOException {
        Product product = productMapper.toEntity(request);
        Image image = imageService.saveImage(file);
        product.setImage(image);
        return productMapper.toResponse(productRepository.save(product));
    }

    public List<ProductResponseDTO> getProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toResponse)
                .toList();
    }

    public ProductResponseDTO update(Long id, ProductRequestDTO request, MultipartFile file) throws IOException {
        return productRepository.findById(id)
                .map(existing -> {
                    try {
                        Product updated = productMapper.toEntity(request);
                        updated.setId(existing.getId());

                        // Handle image update
                        if (file != null && !file.isEmpty()) {
                            Image newImage = imageService.saveImage(file);
                            updated.setImage(newImage);
                        } else {
                            // Keep existing image if no new file provided
                            updated.setImage(existing.getImage());
                        }

                        return productMapper.toResponse(productRepository.save(updated));
                    } catch (IOException e) {
                        throw new RuntimeException("Error processing file upload", e);
                    }
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
