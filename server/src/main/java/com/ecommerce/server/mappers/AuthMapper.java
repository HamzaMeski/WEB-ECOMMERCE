package com.ecommerce.server.mappers;

import com.ecommerce.server.dtos.user.UserResponseDTO;
import com.ecommerce.server.entities.User;
import org.mapstruct.Mapper;

@Mapper
public interface AuthMapper {
    UserResponseDTO toResponse(User user);
}
