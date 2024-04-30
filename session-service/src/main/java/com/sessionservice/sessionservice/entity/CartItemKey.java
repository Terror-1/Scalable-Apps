package com.sessionservice.sessionservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.time.Instant;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyClass
@Builder
public class CartItemKey {

    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
    private String token;


    @PrimaryKeyColumn(ordinal = 0, type = PrimaryKeyType.CLUSTERED)
    private Integer itemId;
}


