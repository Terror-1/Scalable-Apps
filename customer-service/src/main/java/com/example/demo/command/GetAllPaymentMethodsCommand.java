package com.example.demo.command;

import com.example.demo.service.CustomerService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentMethod;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class GetAllPaymentMethodsCommand implements Command<List<PaymentMethod>> {


    private final HttpServletRequest request;

    private final CustomerService customerService;

    @Override
    public List<PaymentMethod> execute() throws StripeException {
        return customerService.getAllPaymentMethods(request);
    }
}
