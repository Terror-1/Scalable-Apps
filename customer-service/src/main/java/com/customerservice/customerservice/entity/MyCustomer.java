package com.customerservice.customerservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyCustomer {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    private String StripeId;

    @Column(unique = true)
    private String email;

    @OneToMany(
            mappedBy = "myCustomer"
    )
    private List<CustomerAddress> customerAddresses;

    @OneToMany(
            mappedBy = "myCustomer"
    )
    private List<Payment> paymentOptions;

    private String firstName;

    private String lastName;

    private String password;
}
