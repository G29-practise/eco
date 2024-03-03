package org.example.eco.product.category;

import org.example.eco.common.repository.GenericRepository;
import org.example.eco.product.category.entity.Category;
import org.springframework.stereotype.Repository;

import java.util.Set;
@Repository
public interface CategoryRepository extends GenericRepository<Category, String> {
    Set<Category> findAllByNameIn(Set<String>names);
}
