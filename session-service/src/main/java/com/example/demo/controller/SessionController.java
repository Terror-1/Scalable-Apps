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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/session")
public class SessionController {
    private final SessionService sessionService;
    private static final Logger LOG = LogManager.getLogger(SessionController.class);

    @GetMapping("/view-cart")
    @ResponseStatus(HttpStatus.OK)
    public CartObject viewCart(HttpServletRequest request) throws StripeException {
        LOG.info("Received request to view cart");
        CartObject cart = sessionService.viewCart(request);
        LOG.info("Returning cart object: {}", cart);
        return cart;
    }

    @GetMapping("/view-cart/empty-cart")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> emptyCart(HttpServletRequest request) {
        LOG.info("Received request to empty cart");
        ResponseEntity<String> response = sessionService.emptyCart(request);
        LOG.info("Empty cart response: {}", response);
        return response;
    }

    @PostMapping("/view-cart/remove-item")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> removeItem(@RequestBody String itemId, HttpServletRequest request) {
        LOG.info("Received request to remove item with ID: {}", itemId);
        ResponseEntity<String> response = sessionService.removeItem(request, itemId);
        LOG.info("Remove item response: {}", response);
        return response;
    }

    @GetMapping("/get-all-sessions") // for testing only
    @ResponseStatus(HttpStatus.OK)
    public List<Session> getAllSessions() {
        LOG.info("Received request to get all sessions");
        List<Session> sessions = sessionService.getAllSessions();
        LOG.info("Returning {} sessions", sessions.size());
        return sessions;
    }

    @PostMapping("/create-order")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> createOrder(HttpServletRequest request) throws StripeException {
        LOG.info("Received request to create order");
        ResponseEntity<String> response = sessionService.createOrder(request);
        LOG.info("Create order response: {}", response);
        return response;
    }
}
