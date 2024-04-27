package com.customerservice.customerservice.controller;

import com.customerservice.customerservice.dto.AddCardObject;
import com.customerservice.customerservice.dto.AddCustomerDto;
import com.customerservice.customerservice.dto.CustomerAddressDto;
import com.customerservice.customerservice.service.CustomerService;
import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/add-customer")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCustomer(@RequestBody AddCustomerDto addCustomerDto) throws StripeException {
        customerService.addCustomer(addCustomerDto);
    }
    @PostMapping("/add-card")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCard(@RequestBody AddCardObject addCardObject) throws StripeException {
        customerService.addCard(addCardObject);
    }

    @PostMapping("/add-address")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAddress(@RequestBody CustomerAddressDto customerAddressDto) throws StripeException {
        customerService.addAddress(customerAddressDto);
    }
}
