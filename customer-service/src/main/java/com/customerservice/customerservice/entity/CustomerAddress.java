package com.customerservice.customerservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerAddress {
    @Id
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private String address;
    private String country;
    private String city;
    private String phone;
    private Integer zipCode;

}
