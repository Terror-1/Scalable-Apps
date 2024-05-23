package com.example.demo.command;

import com.example.demo.service.SessionService;
import com.stripe.exception.StripeException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
public class CreateOrderCommand implements Command<ResponseEntity<String>> {

    private final HttpServletRequest request;
    private final SessionService sessionService;

    @Override
    public ResponseEntity<String> execute() throws StripeException {
        return sessionService.createOrder(request);
    }
}
