package com.example.demo.entity;

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

@Table(value = "reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @PrimaryKeyColumn(name = "product_id", type = PrimaryKeyType.PARTITIONED)
    private String productId;

    @PrimaryKeyColumn(name = "user_id", type = PrimaryKeyType.CLUSTERED)
    private String userId;

    @Column("user_name")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String userName;

    @Column("rating")
    @CassandraType(type = CassandraType.Name.INT) // 1 -> 5
    private Integer rating;

    @Column("review")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String review;
}
