package org.example.eco.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.eco.productSet.dto.ProductSetResponseDto;

import java.util.Set;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartResponseDtoForUser {
    private UUID id;
    private Set<ProductSetResponseDto> products;
}
