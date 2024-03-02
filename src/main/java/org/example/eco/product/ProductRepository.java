package org.example.eco.product;
import org.example.eco.common.repository.GenericRepository;
import org.example.eco.product.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends GenericRepository<Product, UUID> {
    Optional<Product> findByTitle(String title);

}
