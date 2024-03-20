package org.example.eco.wishlist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.eco.product.dto.ProductResponseDto;
import org.example.eco.user.dto.UserResponseDto;

import java.util.Set;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishlistResponseDto {
    private UUID id;
    private UserResponseDto user;
    private Set<ProductResponseDto> products;
}
