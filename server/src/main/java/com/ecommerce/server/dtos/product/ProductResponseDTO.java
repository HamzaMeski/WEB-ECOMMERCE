package com.ecommerce.server.dtos.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDTO {
    private Long id;
    private String name;
    private String nutritionalValue;
    private Float weight; // by kg
    private Float price;
    private Long imageId;
}

