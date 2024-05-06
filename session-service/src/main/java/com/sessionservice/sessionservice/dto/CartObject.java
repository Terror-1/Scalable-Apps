package com.sessionservice.sessionservice.dto;

import com.sessionservice.sessionservice.entity.CartItem;
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
