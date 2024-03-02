package org.example.eco.product;

import org.example.eco.common.repository.GenericRepository;
import org.example.eco.product.entity.Product;

import java.util.UUID;

public interface ProductRepository extends GenericRepository<Product, UUID>{
}