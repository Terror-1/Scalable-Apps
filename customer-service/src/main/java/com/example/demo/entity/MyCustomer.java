package com.example.demo.entity;

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
    private String stripeId;

    @Column(unique = true)
    private String email;

    private String firstName;

    private String lastName;

    private String password;

}
