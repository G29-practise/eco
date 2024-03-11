package org.example.eco.cart.dto;

import lombok.*;
import org.example.eco.productSet.dto.ProductSetResponseDto;
import org.example.eco.user.dto.UserResponseDtoForCartAndOrder;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartResponseDto{
    private UUID id;
    private UserResponseDtoForCartAndOrder user;
    private Set<ProductSetResponseDto> products;
}
