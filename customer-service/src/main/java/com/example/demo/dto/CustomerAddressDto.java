package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomerAddressDto {
    private String firstName;

    private String lastName;

    private String city;

    private String line1;

    private String postalCode;

    private String country;
}
