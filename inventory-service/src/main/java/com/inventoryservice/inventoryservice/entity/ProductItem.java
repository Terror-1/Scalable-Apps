package com.inventoryservice.inventoryservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    private Long id;
    @Column(unique = true)
    private String sku;
    private Integer price;
    @OneToMany(
            mappedBy = "productItem"
    )
    private List<ItemSize> colorsAndSizes;
}
