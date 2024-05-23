package com.example.demo.repository;

import com.example.demo.entity.PopularProducts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PopularProductsRepository extends CrudRepository<PopularProducts, Integer> {


}