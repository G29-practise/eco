package org.example.eco.user.dto;

import lombok.*;
import org.example.eco.cart.dto.CartResponseDtoForUser;
import org.example.eco.order.dto.OrderResponseDtoForUser;
import org.example.eco.user.role.dto.RoleResponseDto;
import org.example.eco.wishlist.dto.WishlistResponseDtoForUser;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto extends UserBaseDto{
    private UUID id;
    private LocalDateTime created;
    private LocalDateTime updated;
    private Set<RoleResponseDto> roles;
    private Set<String> permissions;
    private CartResponseDtoForUser cart;
    private WishlistResponseDtoForUser wishlist;
    private Set<OrderResponseDtoForUser> orders;
}
