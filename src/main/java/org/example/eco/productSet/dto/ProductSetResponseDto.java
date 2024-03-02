package org.example.eco.productSet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.eco.product.entity.Product;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSetResponseDto {
    private UUID id;
    private int quantity;
    private Product product;
    //private Cart cart;
}
