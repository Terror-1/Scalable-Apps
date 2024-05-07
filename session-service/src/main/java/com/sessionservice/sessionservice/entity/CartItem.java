package com.sessionservice.sessionservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

@Table(value = "cart_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {

     @PrimaryKeyColumn(name = "user_id", type = PrimaryKeyType.PARTITIONED)
     private String userID;

     @Column("item_id")
     @PrimaryKeyColumn(name = "item_id", type = PrimaryKeyType.CLUSTERED)
     private String itemId;


     @Column("sku")
     @CassandraType(type = CassandraType.Name.TEXT)
     private String sku;

     @Column("small_mid_large_one_size")
     @CassandraType(type = CassandraType.Name.TEXT)
     private String smallMidLargeOneSize; // xs, s, m, l, xl, xxl, oneSize

     @Column("color")
     @CassandraType(type = CassandraType.Name.TEXT)
     private String color; // xs, s, m, l, xl, xxl, oneSize

     @Column("name")
     @CassandraType(type = CassandraType.Name.TEXT)
     private String name; // xs, s, m, l, xl, xxl, oneSize

     @Column("size_number")
     @CassandraType(type = CassandraType.Name.INT)
     private Integer sizeNumber; // shoes for example: 42, 44, -1


     @Column("item_price")
     @CassandraType(type = CassandraType.Name.DOUBLE)
     private Double itemPrice;

     @Column("quantity")
     @CassandraType(type = CassandraType.Name.INT)
     private Integer quantity;
}