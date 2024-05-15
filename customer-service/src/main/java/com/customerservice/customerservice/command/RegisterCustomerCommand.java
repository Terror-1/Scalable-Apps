package com.customerservice.customerservice.command;

import com.customerservice.customerservice.entity.MyCustomer;
import com.customerservice.customerservice.service.CustomerService;
import com.stripe.exception.StripeException;
import jakarta.servlet.http.HttpServletRequest;
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
