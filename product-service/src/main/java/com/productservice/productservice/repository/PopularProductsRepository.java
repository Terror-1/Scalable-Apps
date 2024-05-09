package com.productservice.productservice.repository;

import com.productservice.productservice.entity.PopularProducts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface PopularProductsRepository extends CrudRepository<PopularProducts, Integer> {


}