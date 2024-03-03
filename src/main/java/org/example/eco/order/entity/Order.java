package org.example.eco.order.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.eco.address.entity.Address;
import org.example.eco.product.entity.Product;
import org.example.eco.user.entity.User;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "`order`")
public class Order {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(mappedBy = "orders")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Product> products;

    @OneToOne(mappedBy = "order")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Address address;

}
