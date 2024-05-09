package com.customerservice.customerservice.controller;

import com.customerservice.customerservice.command.AddCardCommand;
import com.customerservice.customerservice.command.Command;
import com.customerservice.customerservice.dto.AddCustomerDto;
import com.customerservice.customerservice.dto.CustomerAddressDto;
import com.customerservice.customerservice.entity.MyCustomer;
import com.customerservice.customerservice.service.CustomerService;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.param.PaymentIntentCreateParams;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.HttpHeaders;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    @PostMapping("/add-card")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addCard(HttpServletRequest request, @RequestBody String cardToken) throws Exception {
        Command<ResponseEntity<String>> command = new AddCardCommand(request, cardToken, customerService);
        return command.execute();
    }
    @GetMapping("get-all-payment-methods")
    @ResponseStatus(HttpStatus.OK)
    public List<PaymentMethod> getAllPaymentMethods(HttpServletRequest request) throws StripeException {
        return customerService.getAllPaymentMethods(request);
    }

    @PostMapping("/add-address")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addAddress(@RequestBody CustomerAddressDto customerAddressDto, HttpServletRequest request) throws StripeException {

        return customerService.addAddress(customerAddressDto, request);
    }
    @GetMapping("get-address")
    @ResponseStatus(HttpStatus.OK)
    public String getAddress(HttpServletRequest request) throws StripeException {
        return customerService.getAddress(request);
    }

    @PostMapping("/register")
    public ResponseEntity<MyCustomer> registerCustomer(@RequestBody MyCustomer customer) throws StripeException {
        MyCustomer registeredCustomer = customerService.registerCustomer(customer);
        if (registeredCustomer == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(registeredCustomer, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody MyCustomer customer, HttpServletResponse response) {
        String my_token = customerService.authenticate(customer.getEmail(), customer.getPassword());
        if (my_token != null) {
            // Create a cookie with the token
            ResponseCookie cookie = ResponseCookie.from("token", my_token)
                    .maxAge(Duration.ofDays(1)) // Set the cookie's maximum age
                    .path("/") // Set the cookie's path
                    .httpOnly(true) // Set the cookie as HttpOnly to prevent client-side script access
                    .build();

            // Add the cookie to the response header
            response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

            customerService.createSession(customer.getEmail());
            return new ResponseEntity<>(my_token, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        return customerService.logout(request, response);
    }
}
