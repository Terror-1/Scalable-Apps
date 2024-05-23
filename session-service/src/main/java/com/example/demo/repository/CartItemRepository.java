package com.example.demo.repository;

import com.example.demo.entity.CartItem;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public interface CartItemRepository extends CassandraRepository<CartItem, String> {

    @Query("SELECT * FROM cart_item WHERE item_id = ?0 AND user_id = ?1")
    Optional<CartItem> findByItemIdAndUserId(String itemId, String userId);
    @Query("SELECT * FROM cart_item WHERE user_id = ?0")
    List<CartItem> findAllByUserId(String userId);

    @Query("DELETE FROM cart_item WHERE user_id = ?0")
    void deleteAllByUserId(String userId);

    @Query("DELETE FROM cart_item WHERE user_id = ?0 AND item_id = ?1")
    void deleteCartItemByUserIdAndItemId(String userId, String itemId);

}
