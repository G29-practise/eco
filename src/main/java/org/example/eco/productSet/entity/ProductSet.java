package org.example.eco.productSet.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.eco.product.entity.Product;

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
    @JoinColumn(name = "productId", updatable = false)
    private Product productId;
    /*@ManyToOne
    //@JoinColumn(name = "cartId", updatable = false)
    private Cart cartId;*/
}
