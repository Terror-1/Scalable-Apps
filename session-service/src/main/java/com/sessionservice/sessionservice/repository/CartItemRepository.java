package com.sessionservice.sessionservice.repository;

import com.sessionservice.sessionservice.entity.CartItem;
import com.sessionservice.sessionservice.entity.CartItemKey;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public interface CartItemRepository extends CassandraRepository<CartItem, CartItemKey> {
    @Query("SELECT * FROM cart_item WHERE cart_item_key_user_id = ?0")
    List<CartItem> findAllByUserId(String userId);

    @Query("DELETE FROM cart_item WHERE cart_item_key_user_id = ?0")
    void deleteAllByUserId(String userId);

    @Query("DELETE FROM cart_item WHERE cart_item_key_user_id = ?0 AND cart_item_key_item_id = ?1 IF quantity = 1")
    boolean deleteIfQuantityIsOne(String userId, String itemId);

    @Query("UPDATE cart_item SET quantity = quantity - 1 WHERE cart_item_key_user_id = ?0 AND cart_item_key_item_id = ?1 IF quantity > 1")
    boolean decrementQuantityIfGreaterThanOne(String userId, String itemId);
}
