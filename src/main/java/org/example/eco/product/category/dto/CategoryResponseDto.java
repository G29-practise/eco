package org.example.eco.product.category.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.eco.product.dto.ProductResponseDto;
import org.example.eco.product.entity.Product;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponseDto {
    private String name;
    private List<ProductResponseDto> products;
}
