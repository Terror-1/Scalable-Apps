package com.example.demo.repository;

import com.example.demo.entity.MyCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<MyCustomer, Integer> {
    MyCustomer findByEmail(String email);

    MyCustomer findByStripeId(String stripeId);
}
