package com.customerservice.customerservice.repository;

import com.customerservice.customerservice.entity.MyCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<MyCustomer, Integer> {
    MyCustomer findByEmail(String email);
}
