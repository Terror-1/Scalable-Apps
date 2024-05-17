package com.productservice.productservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.PartitionKey;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName = "product")
public class Product {
    @Id
    @GeneratedValue
    private Integer id;
    @jakarta.persistence.Column(unique = true)
    @Field(type = FieldType.Text)
    private String sku; // gucci_black_male_shoe_39

    private String name; //  black shoe

    private double price;

    private String smallMidLargeOneSize; // s, m, l, oneSize

    private Integer sizeNumber; // shoes for example: 42, 44, -1

    private Integer quantity;

    private String color;

    private String imageUrl;

    private String description;

    private String gender; // male, female

    private String category; // clothes, shoes, sunglasses, perfumes, watches, accessories
}
