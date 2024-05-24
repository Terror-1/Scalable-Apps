package com.example.demo.controller;

import com.example.demo.dto.CustomerAddressDto;
import com.example.demo.entity.MyCustomer;
import com.example.demo.service.CustomerService;
import com.example.demo.command.*;
import com.stripe.model.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private static final Logger LOG = LogManager.getLogger(CustomerController.class);
    
    @PostMapping("/add-card")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addCard(HttpServletRequest request, @RequestBody String cardToken) throws Exception {
        LOG.info("Received request to add card with token: {}", cardToken);
        Command<ResponseEntity<String>> command = new AddCardCommand(request, cardToken, customerService);
        ResponseEntity<String> response = command.execute();
        LOG.info("Card added successfully, response: {}", response);
        return response;
    }

    @PostMapping("/set-default-payment-method")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> setDefaultPaymentMethod(HttpServletRequest request, @RequestBody String cardId) throws Exception {
        LOG.info("Received request to set default payment method with card ID: {}", cardId);
        Command<ResponseEntity<String>> command = new SetDefaultPaymentMethodCommand(request, cardId, customerService);
        ResponseEntity<String> response = command.execute();
        LOG.info("Default payment method set successfully, response: {}", response);
        return response;
    }

    @PostMapping("/delete-payment-method")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deletePaymentMethod(HttpServletRequest request, @RequestBody String cardId) throws Exception {
        LOG.info("Received request to delete payment method with card ID: {}", cardId);
        Command<ResponseEntity<String>> command = new DeletePaymentMethodCommand(request, cardId, customerService);
        ResponseEntity<String> response = command.execute();
        LOG.info("Payment method deleted successfully, response: {}", response);
        return response;
    }

    @GetMapping("get-all-payment-methods")
    @ResponseStatus(HttpStatus.OK)
    public List<PaymentMethod> getAllPaymentMethods(HttpServletRequest request) throws Exception {
        LOG.info("Received request to get all payment methods");
        Command<List<PaymentMethod>> command = new GetAllPaymentMethodsCommand(request, customerService);
        List<PaymentMethod> response = command.execute();
        LOG.info("Retrieved all payment methods successfully, response: {}", response);
        return response;
    }

    @GetMapping("get-default-payment-method")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> getDefaultPaymentMethod(HttpServletRequest request) throws Exception {
        LOG.info("Received request to get default payment method");
        Command<ResponseEntity<String>> command = new GetDefaultPaymentMethodCommand(request, customerService);
        ResponseEntity<String> response = command.execute();
        LOG.info("Retrieved default payment method successfully, response: {}", response);
        return response;
    }

    @GetMapping("get-all-orders")
    @ResponseStatus(HttpStatus.OK)
    public List<PaymentIntent> getAllOrders(HttpServletRequest request) throws Exception {
        LOG.info("Received request to get all orders");
        Command<List<PaymentIntent>> command = new GetAllOrdersCommand(request, customerService);
        List<PaymentIntent> response = command.execute();
        LOG.info("Retrieved all orders successfully, response: {}", response);
        return response;
    }

    @PostMapping("/add-address")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addAddress(@RequestBody CustomerAddressDto customerAddressDto, HttpServletRequest request) throws Exception {
        LOG.info("Received request to add address: {}", customerAddressDto);
        Command<ResponseEntity<String>> command = new AddAddressCommand(request, customerAddressDto, customerService);
        ResponseEntity<String> response = command.execute();
        LOG.info("Address added successfully, response: {}", response);
        return response;
    }

    @GetMapping("get-address")
    @ResponseStatus(HttpStatus.OK)
    public Address getAddress(HttpServletRequest request) throws Exception {
        LOG.info("Received request to get address");
        Command<Address> command = new GetAddressCommand(request, customerService);
        Address response = command.execute();
        LOG.info("Retrieved address successfully, response: {}", response);
        return response;
    }

    @PostMapping("/register")
    public ResponseEntity<MyCustomer> registerCustomer(@RequestBody MyCustomer customer) throws Exception {
        LOG.info("Received request to register customer: {}", customer);
        Command<ResponseEntity<MyCustomer>> command = new RegisterCustomerCommand(customer, customerService);
        ResponseEntity<MyCustomer> response = command.execute();
        LOG.info("Customer registered successfully, response: {}", response);
        return response;
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody MyCustomer customer, HttpServletResponse response) throws Exception {
        LOG.info("Received request to authenticate customer: {}", customer);
        Command<ResponseEntity<String>> command = new LoginCommand(response, customer, customerService);
        ResponseEntity<String> loginResponse = command.execute();
        LOG.info("Customer authenticated successfully, response: {}", loginResponse);
        return loginResponse;
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOG.info("Received request to logout");
        Command<ResponseEntity<String>> command = new LogoutCommand(request, response, customerService);
        ResponseEntity<String> logoutResponse = command.execute();
        LOG.info("Customer logged out successfully, response: {}", logoutResponse);
        return logoutResponse;
    }

}
