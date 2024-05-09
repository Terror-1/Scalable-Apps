package com.customerservice.customerservice.command;

import com.customerservice.customerservice.service.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
public class getAllPaymentMethods {
    private final CustomerService customerService;
    private final HttpServletRequest request;


}
