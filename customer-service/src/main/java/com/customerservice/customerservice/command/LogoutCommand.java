package com.customerservice.customerservice.command;

import com.customerservice.customerservice.entity.MyCustomer;
import com.customerservice.customerservice.service.CustomerService;
import com.stripe.exception.StripeException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
public class LogoutCommand implements Command<ResponseEntity<String>> {


    private final HttpServletRequest request;

    private final HttpServletResponse response;

    private final CustomerService customerService;

    @Override
    public ResponseEntity<String> execute() throws StripeException {
        return customerService.logout(request, response);
    }
}
