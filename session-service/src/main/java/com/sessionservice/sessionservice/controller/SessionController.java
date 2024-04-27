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
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    @KafkaListener(topics = "addToCart", groupId = "addToCart")
    public void consumeMessage(AddToCartMessage msg) {
        log.info(format("Consuming the message:: %s", msg));
        sessionService.addToCart(msg);
    }
}
