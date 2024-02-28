package org.example.eco.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.eco.user.role.dto.RoleResponseDto;

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
//    private CartResponseDto cart;
//    private Set<CardResponseDto> cards;
//    private Set<OrderResponseDto> orders;
}
