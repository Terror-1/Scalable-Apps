package com.example.demo.command;

import com.example.demo.entity.MyCustomer;
import com.example.demo.service.CustomerService;
import com.stripe.exception.StripeException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.HttpHeaders;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;

import java.time.Duration;

@AllArgsConstructor
public class LoginCommand implements Command<ResponseEntity<String>> {

    private final HttpServletResponse response;

    private final MyCustomer customer;

    private final CustomerService customerService;

    @Override
    public ResponseEntity<String> execute() throws StripeException {
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
}
