package org.example.eco.cart;

import org.example.eco.cart.entity.Cart;
import org.example.eco.common.repository.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CartRepositoriy  extends GenericRepository<Cart, UUID> {
}
