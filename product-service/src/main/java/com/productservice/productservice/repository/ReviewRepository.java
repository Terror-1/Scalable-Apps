package com.productservice.productservice.repository;

import com.productservice.productservice.entity.Review;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface ReviewRepository extends CassandraRepository<Review, String> {
}
