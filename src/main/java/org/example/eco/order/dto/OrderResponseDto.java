package org.example.eco.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.eco.address.dto.AddressResponseDto;
import org.example.eco.productSet.dto.ProductSetResponseDto;
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
    private Set<ProductSetResponseDto> products;
}
