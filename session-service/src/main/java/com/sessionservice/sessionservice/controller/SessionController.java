package com.sessionservice.sessionservice.controller;

import com.productservice.productservice.dto.AddToCartMessage;
import com.productservice.productservice.entity.Product;
import com.sessionservice.sessionservice.dto.CartObject;
import com.sessionservice.sessionservice.entity.CartItem;
import com.sessionservice.sessionservice.entity.Session;
import com.sessionservice.sessionservice.repository.SessionRepository;
import com.sessionservice.sessionservice.service.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
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
    public CartObject viewCart(HttpServletRequest request) {
        String token = sessionService.getTokenFromCookies(request);
        String userId = sessionService.getIdFromToken(token);
        return sessionService.viewCart(userId);
    }

    @GetMapping("/view-cart/empty-cart")
    @ResponseStatus(HttpStatus.OK)
    public void emptyCart(HttpServletRequest request) {
        String token = sessionService.getTokenFromCookies(request);
        String userId = sessionService.getIdFromToken(token);
        sessionService.emptyCart(userId);
    }

    @GetMapping("/view-cart/remove-item")
    @ResponseStatus(HttpStatus.OK)
    public void removeItem(@RequestBody String itemId, HttpServletRequest request) {
        String token = sessionService.getTokenFromCookies(request);
        String userId = sessionService.getIdFromToken(token);
        sessionService.removeItem(userId, itemId);
    }

    @GetMapping("/get-all-sessions") // for testing only
    @ResponseStatus(HttpStatus.OK)
    public List<Session> getAllSessions() {
        return sessionService.getAllSessions();
    }
}
