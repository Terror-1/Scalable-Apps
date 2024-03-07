package com.sessionservice.sessionservice.repository;

import com.sessionservice.sessionservice.entity.CartItem;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface CartItemRepository extends CassandraRepository<CartItem, String> {
}
