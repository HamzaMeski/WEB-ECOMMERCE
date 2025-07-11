package com.ecommerce.server.repositories;

import com.ecommerce.server.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
