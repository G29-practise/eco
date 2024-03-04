package org.example.eco.productSet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSetCreateDto {
    private int quantity;
    private UUID productId;
    private UUID cartId;
}
