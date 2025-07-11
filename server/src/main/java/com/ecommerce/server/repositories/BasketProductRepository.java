package com.ecommerce.server.repositories;

import com.ecommerce.server.entities.BasketProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketProductRepository extends JpaRepository<BasketProduct, Long> {
}
