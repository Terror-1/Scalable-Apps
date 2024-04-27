package com.customerservice.customerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AddCustomerDto {

    private String email;

    private String stripeId;

    private String firstName;

    private  String lastName;
}
