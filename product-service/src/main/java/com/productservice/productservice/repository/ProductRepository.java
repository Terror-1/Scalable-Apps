package com.productservice.productservice.repository;

import com.productservice.productservice.entity.Product;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

public interface ProductRepository extends CassandraRepository<Product, Integer> {
    List<Product> findByGender(String gender);
    List<Product> findByGenderAndCategory(String gender, String category);


}
