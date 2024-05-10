package com.productservice.productservice.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("popular_products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PopularProducts implements Serializable {
    @Id
    private Integer productId;

    private String name; //  black shoe

    private String imageUrl;
}
