package com.sessionservice.sessionservice.controller;

import com.productservice.productservice.dto.AddToCartMessage;
import com.productservice.productservice.entity.Product;
import com.sessionservice.sessionservice.dto.CartObject;
import com.sessionservice.sessionservice.entity.CartItem;
import com.sessionservice.sessionservice.repository.SessionRepository;
import com.sessionservice.sessionservice.service.SessionService;
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

    @GetMapping("/view-cart/{token}")
    @ResponseStatus(HttpStatus.OK)
    public CartObject viewCart(@PathVariable String token) {
        return sessionService.viewCart(token);
    }

    @PostMapping("/view-cart/purchase/{token}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> purchase(@PathVariable String token) {
        return sessionService.purchase(token);
    }


    @GetMapping("/view-cart/empty-cart/{token}")
    @ResponseStatus(HttpStatus.OK)
    public void emptyCart(@PathVariable String token) {
        sessionService.emptyCart(token);
    }

    @GetMapping("/view-cart/remove-item/{token}")
    @ResponseStatus(HttpStatus.OK)
    public void removeItem(@PathVariable String token, @RequestBody String itemId) {
        sessionService.removeItem(token, itemId);
    }
}
