package com.externalDTOs.externalDTOs.dtos;

import lombok.*;
import com.externalDTOs.externalDTOs.dtos.CartItem;

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
