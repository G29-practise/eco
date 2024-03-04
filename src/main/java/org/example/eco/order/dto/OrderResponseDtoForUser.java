package org.example.eco.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.eco.address.dto.AddressResponseDto;
import org.example.eco.product.dto.ProductResponseDto;

import java.util.Set;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDtoForUser {
    private UUID id;
    private AddressResponseDto address;
    private Set<ProductResponseDto> products;
}
