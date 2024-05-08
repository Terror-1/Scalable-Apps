package com.productservice.productservice.repository;

import com.productservice.productservice.entity.Review;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import java.util.*;

public interface ReviewRepository extends CassandraRepository<Review, String> {
    @Query("INSERT INTO reviews (product_id, user_id, user_name, rating, review) VALUES (?0, ?1, ?2, ?3, ?4)")
    void addReview(String productId, String userId, String userName, Integer rating, String reviewBody);

    @Query("SELECT * FROM reviews WHERE product_id = ?0")
    List<Review> findAllByProductId(String productId);

    @Query("DELETE FROM reviews WHERE user_id = ?0 AND product_id = ?1")
    void deleteByUserIdAndProductId(String userId, String productId);

    @Query("SELECT * FROM reviews WHERE user_id = ?0 AND product_id = ?1 LIMIT 1")
    Review findByUserIdAndProductId(String userId, String productId);
}
