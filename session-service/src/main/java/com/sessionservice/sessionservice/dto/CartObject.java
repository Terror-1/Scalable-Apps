package com.sessionservice.sessionservice.dto;

import com.sessionservice.sessionservice.entity.CartItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartObject {
    private Double totalAmount;
    private List<CartItem> products;
}
