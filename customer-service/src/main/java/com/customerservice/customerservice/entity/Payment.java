package com.customerservice.customerservice.entity;

import com.sun.jdi.IntegerType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
    @Id
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private BigDecimal cashScore;

    private String paymentType; // "creditCard" or "paypal"

    private String securityNumber; // Optional, only for "credit_card"

    private String cardNumber; // Optional, only for "credit_card"

    private String cardHolderName; // Optional, only for "credit_card"

    private Date expirationDate; // Optional, only for "credit_card"

    private String paypalEmail; // Optional, only for "paypal"
}
