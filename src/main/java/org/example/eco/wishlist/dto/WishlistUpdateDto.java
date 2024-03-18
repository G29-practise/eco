package org.example.eco.wishlist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.eco.user.entity.User;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishlistUpdateDto
{
    private User user;
}
