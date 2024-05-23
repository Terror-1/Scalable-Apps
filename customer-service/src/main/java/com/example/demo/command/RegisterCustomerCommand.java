package com.example.demo.command;

import com.example.demo.entity.MyCustomer;
import com.example.demo.service.CustomerService;
import com.stripe.exception.StripeException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
public class RegisterCustomerCommand implements Command<ResponseEntity<MyCustomer>> {


    private final MyCustomer customer;

    private final CustomerService customerService;

    @Override
    public ResponseEntity<MyCustomer> execute() throws StripeException {
        MyCustomer registeredCustomer = customerService.registerCustomer(customer);
        if (registeredCustomer == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(registeredCustomer, HttpStatus.CREATED);
    }
}
