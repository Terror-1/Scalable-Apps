package com.sessionservice.sessionservice.repository;

import com.sessionservice.sessionservice.entity.CartItem;
import com.sessionservice.sessionservice.entity.CartItemKey;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public interface CartItemRepository extends CassandraRepository<CartItem, CartItemKey> {
    @Query("SELECT * FROM cart_item WHERE cart_item_key_token = ?0")
    List<CartItem> findAllByToken(String token);

    @Query("DELETE FROM cart_item WHERE cart_item_key_token = ?0")
    void deleteAllByToken(String token);

}
