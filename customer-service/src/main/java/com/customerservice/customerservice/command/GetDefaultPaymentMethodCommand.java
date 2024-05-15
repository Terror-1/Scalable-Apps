package com.customerservice.customerservice.command;

import com.customerservice.customerservice.service.CustomerService;
import com.stripe.exception.StripeException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
public class GetDefaultPaymentMethodCommand implements Command<ResponseEntity<String>> {


    private final HttpServletRequest request;

    private final CustomerService customerService;

    @Override
    public ResponseEntity<String> execute() throws StripeException {
        return customerService.getDefaultPaymentMethod(request);
    }
}
