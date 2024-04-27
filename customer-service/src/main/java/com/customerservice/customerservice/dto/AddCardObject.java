package com.customerservice.customerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AddCardObject {
    private String stripeUserId;

    private String cardNumber;

    private String expMonth;

    private String expYear;

    private String cvc;
}
