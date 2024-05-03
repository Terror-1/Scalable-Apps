package com.customerservice.customerservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private MyCustomer myCustomer;

    @Column(unique = true)
    private String stripeCardId;

    private String cardHolderName;
}
