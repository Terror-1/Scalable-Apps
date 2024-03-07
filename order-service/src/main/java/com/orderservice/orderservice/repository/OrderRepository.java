package com.orderservice.orderservice.repository;

import com.orderservice.orderservice.entity.Order;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface OrderRepository extends CassandraRepository<Order, Long> {
}
