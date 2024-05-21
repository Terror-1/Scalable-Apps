// src/main/java/com/externalDTOs/externalDTOs/entity/CartItem.java
package com.externalDTOs.externalDTOs.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {
    private String userID;
    private String itemId;
    private String sku;
    private String smallMidLargeOneSize;
    private String color;
    private String name;
    private Integer sizeNumber;
    private Double itemPrice;
    private Integer quantity;
}
