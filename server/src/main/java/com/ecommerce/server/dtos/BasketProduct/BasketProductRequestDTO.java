package com.ecommerce.server.dtos.BasketProduct;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasketProductRequestDTO {
    private Long productId;
    private Integer quantity;
}
