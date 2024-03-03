package org.example.eco.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.eco.product.category.dto.CategoryResponseDto;
import org.example.eco.productSet.dto.ProductSetResponseDto;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductResponseDto extends ProductBaseDto{
    private UUID id;
    private int rating;
    //private Set<WishlistResponseDto> wishlists;
    //private Set<OrderResponseDto> orders;
    private Set<ProductSetResponseDto> productSets;
    private Set<String> categories;
    private LocalDateTime created;
    private LocalDateTime updated;
}
