package com.productservice.productservice.repository;

import com.productservice.productservice.entity.Product;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

public interface ProductRepository extends CassandraRepository<Product, Integer> {
}
