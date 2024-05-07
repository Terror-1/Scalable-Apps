package com.sessionservice.sessionservice.controller;

import com.sessionservice.sessionservice.dto.CartObject;
import com.sessionservice.sessionservice.entity.Session;
import com.sessionservice.sessionservice.service.SessionService;
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
        String token = sessionService.getTokenFromCookies(request);
        String userId = sessionService.getIdFromToken(token);
        return sessionService.viewCart(request);
    }

    @GetMapping("/view-cart/empty-cart")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> emptyCart(HttpServletRequest request) {
        String token = sessionService.getTokenFromCookies(request);
        String userId = sessionService.getIdFromToken(token);
        return sessionService.emptyCart(request);
    }

    @PostMapping("/view-cart/remove-item")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> removeItem(@RequestBody String itemId, HttpServletRequest request) {
        String token = sessionService.getTokenFromCookies(request);
        String userId = sessionService.getIdFromToken(token);
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
