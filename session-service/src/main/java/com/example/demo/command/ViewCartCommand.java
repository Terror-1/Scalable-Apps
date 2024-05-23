package com.example.demo.command;

import com.example.demo.service.SessionService;
import com.example.demo.dto.CartObject;
import com.stripe.exception.StripeException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ViewCartCommand implements Command<CartObject> {

    private final HttpServletRequest request;
    private final SessionService sessionService;

    @Override
    public CartObject execute() throws StripeException {
        return sessionService.viewCart(request);
    }
}
