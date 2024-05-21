package com.externalDTOs.externalDTOs.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AddToCartMessage {
    private String userId;
    private Integer productId;
    private String color;
    private String description;
    private String sku; // black_male_shoe
    private String name;
    private double price;
    private String smallMidLargeOneSize; // s, m, l, oneSize
    private Integer sizeNumber; // shoes for example: 42, 44, -1
}
