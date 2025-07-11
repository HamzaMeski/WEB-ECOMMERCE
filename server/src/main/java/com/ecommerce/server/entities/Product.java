package com.ecommerce.server.entities;

import jakarta.persistence.*;
import lombok.*;


@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String NutritionalValue;

    private Integer weight; // by kg

    @ManyToOne
    private Basket basket;
}
