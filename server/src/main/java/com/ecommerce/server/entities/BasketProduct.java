package com.ecommerce.server.entities;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "basket_product")
public class BasketProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    @ManyToOne
    private Basket basket;

    @ManyToOne
    private Product product;
}
