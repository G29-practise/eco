package org.example.eco.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.eco.address.dto.AddressResponseDto;
import org.example.eco.product.dto.ProductResponseDto;
import org.example.eco.user.dto.UserResponseDtoForCartAndOrder;

import java.util.Set;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderResponseDto{
    private UUID id;
    private AddressResponseDto address;
    private UserResponseDtoForCartAndOrder user;
    private Set<ProductResponseDto> products;
}
