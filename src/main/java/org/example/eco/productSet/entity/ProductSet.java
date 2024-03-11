package org.example.eco.productSet.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.eco.cart.entity.Cart;
import org.example.eco.order.entity.Order;
import org.example.eco.product.entity.Product;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductSet {
    @Id
    private UUID id;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
    @ManyToMany(fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinTable(
            name = "product_set_order",
            joinColumns = @JoinColumn(name = "product_set_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private Set<Order> orders;
}
