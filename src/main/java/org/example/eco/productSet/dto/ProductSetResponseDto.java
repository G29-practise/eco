package org.example.eco.productSet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.eco.order.dto.OrderResponseDto;
import org.example.eco.product.dto.ProductResponseDto;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSetResponseDto {
    private UUID id;
    private int quantity;
    private ProductResponseDto product;
}
