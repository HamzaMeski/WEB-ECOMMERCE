package com.ecommerce.server.mappers;

import com.ecommerce.server.dtos.product.ProductRequestDTO;
import com.ecommerce.server.dtos.product.ProductResponseDTO;
import com.ecommerce.server.entities.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(ProductRequestDTO productRequestDTO);
    ProductResponseDTO toResponse(Product product) ;
}
