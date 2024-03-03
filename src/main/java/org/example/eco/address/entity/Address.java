package org.example.eco.address.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.*;
import org.example.eco.order.entity.Order;
import org.example.eco.user.entity.User;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Address {
    @Id
    private UUID id;
    private String city;
    private String country;
    private int postCode;
    private String region;
    @OneToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Order order;
}
