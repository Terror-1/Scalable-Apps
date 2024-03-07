package com.inventoryservice.inventoryservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemSize {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_item_id")
    private ProductItem productItem;

    private String smallMidLarge; // xs, s, m, l, xl, xxl

    private Integer sizeNumber; // shoes for example: 42, 44,

    private Integer quantity;
}
