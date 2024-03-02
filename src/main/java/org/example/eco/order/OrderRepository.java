package org.example.eco.order;

import org.example.eco.common.repository.GenericRepository;
import org.example.eco.order.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends GenericRepository<Order, UUID> {
}
