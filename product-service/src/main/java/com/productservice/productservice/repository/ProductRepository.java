package com.productservice.productservice.repository;

import com.productservice.productservice.entity.PopularProducts;
import com.productservice.productservice.entity.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByGender(String gender);
    List<Product> findByGenderAndCategory(String gender, String category);
    Optional<Product> findBySku(String sku);
    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.quantity = p.quantity - :cartAmount WHERE p.sku = :sku AND p.quantity >= :cartAmount")
    int updateProductQuantityBySku(@Param("sku") String sku, @Param("cartAmount") int cartAmount);

    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.quantity = p.quantity + :num WHERE p.sku = :sku")
    void increaseQuantityBySku(@Param("sku") String sku, @Param("num") int num);


    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE %:query% OR LOWER(p.sku) LIKE %:query% OR LOWER(p.description) LIKE %:query%")
    List<Product> searchByNameOrSkuOrDescriptionContaining(@Param("query") String query);
}

