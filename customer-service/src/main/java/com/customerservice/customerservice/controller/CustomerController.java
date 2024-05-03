package com.customerservice.customerservice.controller;

import com.customerservice.customerservice.dto.AddCardObject;
import com.customerservice.customerservice.dto.AddCustomerDto;
import com.customerservice.customerservice.dto.CustomerAddressDto;
import com.customerservice.customerservice.entity.MyCustomer;
import com.customerservice.customerservice.service.CustomerService;
import com.stripe.exception.StripeException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.HttpHeaders;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/add-customer")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCustomer(@RequestBody AddCustomerDto addCustomerDto) throws StripeException {
        customerService.addCustomer(addCustomerDto);
    }
    @PostMapping("/add-card")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCard(@RequestBody AddCardObject addCardObject) throws StripeException {
        customerService.addCard(addCardObject);
    }

    @PostMapping("/add-address")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAddress(@RequestBody CustomerAddressDto customerAddressDto) throws StripeException {
        customerService.addAddress(customerAddressDto);
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

            return new ResponseEntity<>(my_token, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<MyCustomer> registerCustomer(@RequestBody MyCustomer customer) {
        System.out.println("Received customer: " + customer); // Debugging line

        MyCustomer registeredCustomer = customerService.registerCustomer(customer);
        if (registeredCustomer == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(registeredCustomer, HttpStatus.CREATED);
    }

    @PostMapping("/logout") // should kill
    public ResponseEntity<String> logout(HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie.from("token", null)
                .maxAge(0)
                .path("/")
                .httpOnly(true)
                .build();

        // Add the expired cookie to the response header to overwrite the existing one
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        // Return a response entity indicating the logout was successful
        return new ResponseEntity<>("Logged out successfully", HttpStatus.OK);
    }
}
