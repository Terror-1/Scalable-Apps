package com.example.demo.command;

import com.example.demo.service.CustomerService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class GetAllOrdersCommand implements Command<List<PaymentIntent>> {


    private final HttpServletRequest request;

    private final CustomerService customerService;

    @Override
    public List<PaymentIntent> execute() throws StripeException {
        return customerService.getAllOrders(request);
    }
}
