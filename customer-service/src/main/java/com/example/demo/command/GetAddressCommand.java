package com.example.demo.command;

import com.example.demo.service.CustomerService;
import com.stripe.exception.StripeException;
import com.stripe.model.Address;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetAddressCommand implements Command<Address> {


    private final HttpServletRequest request;


    private final CustomerService customerService;

    @Override
    public Address execute() throws StripeException {
        return customerService.getAddress(request);
    }
}
