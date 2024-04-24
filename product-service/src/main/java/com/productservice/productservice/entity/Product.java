package com.productservice.productservice.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.PartitionKey;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.util.*;

@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue
    private Integer id;
    @jakarta.persistence.Column(unique = true)
    private String sku; // black_male_shoe
    private String name;
    private double price;
    private String smallMidLargeOneSize; // s, m, l, oneSize
    private Integer sizeNumber; // shoes for example: 42, 44, -1
    private Integer quantity;
    private String description;
    private String gender; // male, female
    private String category; // clothes, shoes, sunglasses, perfumes, watches, accessories
    //    @OneToMany(
//            mappedBy = "productItem"
//    )
//    @JsonManagedReference
//    private List<ItemSize> colorsAndSizes;
}
//public class Product {
//    // note in cassandra it requires you to add the pk not auto-generated like sql
//    // sku like: iphone_13, gucci_shirt
//    @Id @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
//    private String sku;
//
//    @Column @CassandraType(type = CassandraType.Name.DECIMAL)
//    private Double price;
//
//    @Column @CassandraType(type = CassandraType.Name.TEXT)
//    private String name;
//
//    @Column @CassandraType(type = CassandraType.Name.TEXT)
//    private String description;
//
//    @Column @CassandraType(type = CassandraType.Name.TEXT)
//    private String gender;  // male, female or unisex
//
//    @Column @CassandraType(type = CassandraType.Name.TEXT)
//    private String category;
//
//}
// another implementation for using category as the partition key, this means each bucket will have a category
// efficient for searching by category
//
//public class Product {
//    @Id
//    @PrimaryKeyColumn(name = "category", type = PrimaryKeyType.PARTITIONED)
//    private String category;
//
//    @Column
//    @PrimaryKeyColumn(name = "id", type = PrimaryKeyType.CLUSTERED)
//    private UUID id = Uuids.timeBased(); // Generate a time-based UUID as the clustering key
//
//    @Column
//    @CassandraType(type = CassandraType.Name.DECIMAL)
//    private Integer price;
//
//    @Column
//    @CassandraType(type = CassandraType.Name.TEXT)
//    private String name;
//
//    @Column
//    @CassandraType(type = CassandraType.Name.TEXT)
//    private String description;
//
//    @Column
//    @CassandraType(type = CassandraType.Name.TEXT)
//    private String gender;
//}

