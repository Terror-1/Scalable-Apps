package com.inventoryservice.inventoryservice.repository;

import com.inventoryservice.inventoryservice.entity.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface ProductItemRepository extends JpaRepository<ProductItem, Long> {
    ProductItem findBySku(String sku);
}
