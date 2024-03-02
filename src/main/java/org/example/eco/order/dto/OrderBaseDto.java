package org.example.eco.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.eco.address.entity.Address;
import org.example.eco.cart.entity.Cart;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderBaseDto {
    private Address address;
    private UUID user_Id;
    private Cart cart;
}
