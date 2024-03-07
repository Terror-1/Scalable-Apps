package com.orderservice.orderservice.entity;
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
public class OrderItem {
    @Id
    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
    private Long userID;

    @Column
    @CassandraType(type = CassandraType.Name.TEXT)
    private String sku;

    @Column
    @CassandraType(type = CassandraType.Name.DOUBLE)
    private Double piecePrice;

    @Column @CassandraType(type = CassandraType.Name.INT)
    private Integer quantity;

    @Column
    @CassandraType(type = CassandraType.Name.TEXT)
    private String smallMidLargeOneSize; // xs, s, m, l, xl, xxl, oneSize

    @Column
    @CassandraType(type = CassandraType.Name.INT)
    private Integer sizeNumber; // shoes for example: 42, 44,
}
