package org.example.eco.product.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
    //private Set<OrderResponseDto> orders;
//    private Set<ProductSetResponseDto> productSets;
    private String fileName;
    private String contentType;
    @Column(length = Integer.MAX_VALUE)
    private Set<String> categories;
    private LocalDateTime created;
    private LocalDateTime updated;
}
