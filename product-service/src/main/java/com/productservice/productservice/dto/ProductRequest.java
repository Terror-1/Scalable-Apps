package com.productservice.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
