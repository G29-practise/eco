package org.example.eco.product.category;

import org.example.eco.common.repository.GenericRepository;
import org.example.eco.product.category.entity.Category;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
@Repository
public interface CategoryRepository extends GenericRepository<Category, UUID> {
    Set<Category> findAllByNameIn(Set<String>names);
    Optional<Category> findByName(String name);
}
