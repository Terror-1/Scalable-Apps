package com.inventoryservice.inventoryservice.repository;

import com.inventoryservice.inventoryservice.entity.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ProductItem, Long> {
}
