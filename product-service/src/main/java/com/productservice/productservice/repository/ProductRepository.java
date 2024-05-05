package com.productservice.productservice.repository;

import com.productservice.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.*;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByGender(String gender);
    List<Product> findByGenderAndCategory(String gender, String category);
    Optional<Product> findBySku(String sku);
}
