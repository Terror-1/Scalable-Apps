package com.customerservice.customerservice.repository;

import com.customerservice.customerservice.entity.MyCustomer;
import com.stripe.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<MyCustomer, Integer> {
    MyCustomer findByEmail(String email);

    MyCustomer findByStripeId(String stripeId);
}
