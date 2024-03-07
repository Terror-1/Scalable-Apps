package com.productservice.productservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.PartitionKey;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    // note in cassandra it requires you to add the pk not auto-generated like sql
    // sku like: iphone_13, gucci_shirt
    @Id @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
    private String sku;

    @Column @CassandraType(type = CassandraType.Name.DECIMAL)
    private Double price;

    @Column @CassandraType(type = CassandraType.Name.TEXT)
    private String name;

    @Column @CassandraType(type = CassandraType.Name.TEXT)
    private String description;

    @Column @CassandraType(type = CassandraType.Name.TEXT)
    private String gender;  // male, female or unisex

    @Column @CassandraType(type = CassandraType.Name.TEXT)
    private String category;

}
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