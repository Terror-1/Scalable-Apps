package com.example.demo.controller;

import com.example.demo.entity.Session;
import com.example.demo.dto.CartObject;
import com.example.demo.service.SessionService;
import com.stripe.exception.StripeException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;


import static java.lang.String.format;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/session")
public class SessionController {
    private final SessionService sessionService;

    @GetMapping("/view-cart")
    @ResponseStatus(HttpStatus.OK)
    public CartObject viewCart(HttpServletRequest request) throws StripeException {
        return sessionService.viewCart(request);
    }

    @GetMapping("/view-cart/empty-cart")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> emptyCart(HttpServletRequest request) {
        return sessionService.emptyCart(request);
    }

    @PostMapping("/view-cart/remove-item")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> removeItem(@RequestBody String itemId, HttpServletRequest request) {
        return sessionService.removeItem(request, itemId);
    }

    @GetMapping("/get-all-sessions") // for testing only
    @ResponseStatus(HttpStatus.OK)
    public List<Session> getAllSessions() {
        return sessionService.getAllSessions();
    }

    @PostMapping("/create-order")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> createOrder(HttpServletRequest request) throws StripeException {
        return sessionService.createOrder(request);
    }

}
