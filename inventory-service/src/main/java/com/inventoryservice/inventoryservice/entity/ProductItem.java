package com.inventoryservice.inventoryservice.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductItem {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String sku; // black_shoe
    private double price;
//    @OneToMany(
//            mappedBy = "productItem"
//    )
//    @JsonManagedReference
//    private List<ItemSize> colorsAndSizes;
    private String smallMidLargeOneSize; // xs, s, m, l, xl, xxl, oneSize

    private Integer sizeNumber; // shoes for example: 42, 44, - 1

    private Integer quantity;
}
