package org.example.eco.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.eco.productSet.dto.ProductSetResponseDto;

import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartUpdateDto{
    private Set<ProductSetResponseDto> products;
}
