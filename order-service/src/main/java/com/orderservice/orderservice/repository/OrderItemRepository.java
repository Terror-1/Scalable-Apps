package com.orderservice.orderservice.repository;

import com.orderservice.orderservice.entity.OrderItem;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface OrderItemRepository extends CassandraRepository<OrderItem, Long> {
}
