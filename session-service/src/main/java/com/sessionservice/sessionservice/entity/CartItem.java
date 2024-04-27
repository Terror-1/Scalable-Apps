package com.sessionservice.sessionservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {

    @PrimaryKey
    private CartItemKey cartItemKey;

    @Column
    @CassandraType(type = CassandraType.Name.TEXT)
    private String sku;

    @Column
    @CassandraType(type = CassandraType.Name.TEXT)
    private String smallMidLargeOneSize; // xs, s, m, l, xl, xxl, oneSize

    @Column
    @CassandraType(type = CassandraType.Name.TEXT)
    private String color; // xs, s, m, l, xl, xxl, oneSize

    @Column
    @CassandraType(type = CassandraType.Name.TEXT)
    private String name; // xs, s, m, l, xl, xxl, oneSize

    @Column
    @CassandraType(type = CassandraType.Name.INT)
    private Integer sizeNumber; // shoes for example: 42, 44, -1


    @Column
    @CassandraType(type = CassandraType.Name.DOUBLE)
    private Double itemPrice;

    @Column
    @CassandraType(type = CassandraType.Name.INT)
    private Integer quantity;
}
