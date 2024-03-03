package org.example.eco.product.category.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;
import org.example.eco.product.entity.Product;

import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    private String name;
    @ManyToMany(mappedBy = "categories")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Product> products;
}
