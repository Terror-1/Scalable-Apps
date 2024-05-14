package com.customerservice.customerservice.command;

import com.customerservice.customerservice.service.CustomerService;
import com.stripe.exception.StripeException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
public class SetDefaultPaymentMethodCommand implements Command<ResponseEntity<String>> {


    private final HttpServletRequest request;

    private final String cardToken;

    private final CustomerService customerService;

    @Override
    public ResponseEntity<String> execute() throws StripeException {
        return customerService.setDefaultPaymentMethod(request, cardToken);
    }
}
