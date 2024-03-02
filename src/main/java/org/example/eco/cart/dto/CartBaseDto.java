package org.example.eco.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartBaseDto {
  private UUID user_Id;
  private Set<UUID> productSet_Id;
}
