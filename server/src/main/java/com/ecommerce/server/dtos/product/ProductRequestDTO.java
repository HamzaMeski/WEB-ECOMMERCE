package com.ecommerce.server.dtos.product;

import lombok.Getter;

@Getter
public class ProductRequestDTO {
    private String name;
    private String NutritionalValue;
    private Integer weight; // by kg
}
