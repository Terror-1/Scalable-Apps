package com.productservice.productservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @PrimaryKeyColumn(name = "sku", type = PrimaryKeyType.PARTITIONED)
    private String sku;

    @Column("rating")
    @CassandraType(type = CassandraType.Name.DECIMAL) // 1 -> 5
    private Integer rating;

    @Column("review")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String review;
}
