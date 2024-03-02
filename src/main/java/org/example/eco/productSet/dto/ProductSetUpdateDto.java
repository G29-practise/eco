package org.example.eco.productSet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.eco.product.entity.Product;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSetUpdateDto {
    private int quantity;
    private Product product;
    //private Cart cart;
}
