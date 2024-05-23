package com.example.demo.command;

import com.example.demo.service.CustomerService;
import com.stripe.exception.StripeException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
public class DeletePaymentMethodCommand implements Command<ResponseEntity<String>> {


    private final HttpServletRequest request;

    private final String cardToken;

    private final CustomerService customerService;

    @Override
    public ResponseEntity<String> execute() throws StripeException {
        return customerService.deletePaymentMethod(request, cardToken);
    }
}
