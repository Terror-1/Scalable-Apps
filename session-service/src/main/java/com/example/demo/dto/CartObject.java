package com.example.demo.dto;

import com.example.demo.entity.CartItem;
import lombok.*;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CartObject {
    private String userId;
    private String paymentMethodId;
    private Double totalAmount;
    private List<CartItem> products;
}
