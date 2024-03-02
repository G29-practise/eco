package org.example.eco.product.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.eco.product.category.entity.Category;
import org.example.eco.productSet.entity.ProductSet;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "`product`")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String brand;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private int discount;
    @Column(nullable = false)
    private double weight;
    @Column(nullable = false)
    private double price;
    /*@OneToMany(mappedBy = "product")
    private Set<Rating> ratings;
    @OneToMany(mappedBy = "productId")
    private Set<ProductSet> productSets;
    @ManyToMany(mappedBy = "products")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Wishlist> wishlists;
    @ManyToMany(mappedBy = "products")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Order> orders;*/
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_name")
    )
    private Set<Category>categories;
    @CreatedDate
    private LocalDateTime create_time;
    @LastModifiedDate
    private LocalDateTime update_time;

}
