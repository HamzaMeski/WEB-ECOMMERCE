package com.ecommerce.server.dtos.basket;

import com.ecommerce.server.dtos.BasketProduct.BasketProductRequestDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BasketRequestDTO {
    private List<BasketProductRequestDTO> products;
}
