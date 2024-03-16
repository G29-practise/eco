package org.example.eco.product.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.eco.product.category.entity.Category;
import org.example.eco.productSet.entity.ProductSet;
import org.example.eco.rating.entity.Rating;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "`product`")
@EntityListeners(AuditingEntityListener.class)
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
    private int discount;
    @Column(nullable = false)
    private double weight;
    @Column(nullable = false)
    private double price;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Rating> ratings;

//    private String fileName;
//    private String contentType;
//    @Column(length = Integer.MAX_VALUE)
//    private String img;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    @JoinTable(
//            name = "product_order",
//            joinColumns = @JoinColumn(name = "product_id"),
//            inverseJoinColumns = @JoinColumn(name = "order_id")
//    )
//    private Set<Order> orders;

//    @ManyToMany(mappedBy = "products")
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private Set<Cart> carts;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<ProductSet> productSets;

//    @ManyToMany(mappedBy = "products")
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private Set<Wishlist> wishlists;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_name")
    )
    private Set<Category> categories;
    @CreatedDate
    private LocalDateTime created;
    @LastModifiedDate
    private LocalDateTime updated;
}
