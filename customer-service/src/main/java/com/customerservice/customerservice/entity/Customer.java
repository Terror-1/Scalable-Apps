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
public class Customer {
    @Id
    private Long id;

    @Column(unique = true)
    private String email;

    @OneToMany(
            mappedBy = "customer"
    )
    private List<CustomerAddress> customerAddresses;

    @OneToMany(
            mappedBy = "customer"
    )
    private List<Payment> paymentOptions;

    private String firstName;

    private String lastName;

    private String password;
}
