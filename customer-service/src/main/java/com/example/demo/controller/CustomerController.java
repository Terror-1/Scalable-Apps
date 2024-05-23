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

    @PostMapping("/set-default-payment-method")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> setDefaultPaymentMethod(HttpServletRequest request, @RequestBody String cardId) throws Exception {
        Command<ResponseEntity<String>> command = new SetDefaultPaymentMethodCommand(request, cardId, customerService);
        return command.execute();
    }

    @PostMapping("/delete-payment-method")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deletePaymentMethod(HttpServletRequest request, @RequestBody String cardId) throws Exception {
        Command<ResponseEntity<String>> command = new DeletePaymentMethodCommand(request, cardId, customerService);
        return command.execute();
    }

    @GetMapping("get-all-payment-methods")
    @ResponseStatus(HttpStatus.OK)
    public List<PaymentMethod> getAllPaymentMethods(HttpServletRequest request) throws Exception {
        Command<List<PaymentMethod>> command = new GetAllPaymentMethodsCommand(request, customerService);
        return command.execute();
    }

    @GetMapping("get-default-payment-method")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> getDefaultPaymentMethod(HttpServletRequest request) throws Exception {
        Command<ResponseEntity<String>> command = new GetDefaultPaymentMethodCommand(request, customerService);
        return command.execute();
    }

    @GetMapping("get-all-orders")
    @ResponseStatus(HttpStatus.OK)
    public List<PaymentIntent> getAllOrders(HttpServletRequest request) throws Exception {
        Command<List<PaymentIntent>> command = new GetAllOrdersCommand(request, customerService);
        return command.execute();
    }

    @PostMapping("/add-address")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addAddress(@RequestBody CustomerAddressDto customerAddressDto, HttpServletRequest request) throws Exception {
        Command<ResponseEntity<String>> command = new AddAddressCommand(request, customerAddressDto, customerService);
        return command.execute();
    }
    @GetMapping("get-address")
    @ResponseStatus(HttpStatus.OK)
    public Address getAddress(HttpServletRequest request) throws Exception {
        Command<Address> command = new GetAddressCommand(request, customerService);
        return command.execute();
    }

    @PostMapping("/register")
    public ResponseEntity<MyCustomer> registerCustomer(@RequestBody MyCustomer customer) throws Exception {
        Command<ResponseEntity<MyCustomer>> command = new RegisterCustomerCommand(customer, customerService);
        return command.execute();
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody MyCustomer customer, HttpServletResponse response) throws Exception {
        Command<ResponseEntity<String>> command = new LoginCommand(response ,customer, customerService);
        return command.execute();
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Command<ResponseEntity<String>> command = new LogoutCommand(request , response, customerService);
        return command.execute();
    }

}
