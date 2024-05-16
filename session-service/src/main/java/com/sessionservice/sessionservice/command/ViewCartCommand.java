package com.sessionservice.sessionservice.command;

import com.sessionservice.sessionservice.dto.CartObject;
import com.sessionservice.sessionservice.service.SessionService;
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
