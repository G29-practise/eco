package org.example.eco.product;

import org.example.eco.common.repository.GenericRepository;
import org.example.eco.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends GenericRepository<Product, UUID> {
    Optional<Product> findByTitle(String title);

    @Query("SELECT p FROM Product p ORDER BY p.created DESC")
    Page<Product> findRecentlyAdded(Pageable pageable);

    @Query("SELECT p FROM Product p JOIN p.ratings r GROUP BY p.id ORDER BY AVG(r.stars) DESC")
    Page<Product> findTopRated(Pageable pageable);
}