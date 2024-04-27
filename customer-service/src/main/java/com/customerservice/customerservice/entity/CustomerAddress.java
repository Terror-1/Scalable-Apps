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
    private Integer id;
    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private MyCustomer myCustomer;
    private String line1;
    private String city;
    private String postalCode;
    private String country;

}
