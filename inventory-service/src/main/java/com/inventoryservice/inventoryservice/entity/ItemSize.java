package com.inventoryservice.inventoryservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemSize {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "product_item_id")
    private ProductItem productItem;

    private String sku;

    private String smallMidLargeOneSize; // xs, s, m, l, xl, xxl, oneSize

    private Integer sizeNumber; // shoes for example: 42, 44, - 1

    private Integer quantity;
}
