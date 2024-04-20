package com.inventoryservice.inventoryservice.dto;

import com.inventoryservice.inventoryservice.entity.ItemSize;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductRequest {
    private String sku; // black_shoe
    private double price;
    private String smallMidLargeOneSize; // xs, s, m, l, xl, xxl, oneSize
    private Integer sizeNumber; // shoes for example: 42, 44, - 1
    private Integer quantity;
}
