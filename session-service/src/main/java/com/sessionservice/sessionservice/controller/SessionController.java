package com.sessionservice.sessionservice.controller;

import com.sessionservice.sessionservice.repository.SessionRepository;
import com.sessionservice.sessionservice.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/session")
public class SessionController {
    private final SessionService sessionService;

    @GetMapping({"/addToCart/{sku}"})
    public void addToCart(@PathVariable(name="sku") String sku) {

    }
    @GetMapping({"/removeFromCart/{sku}"})
    public void removeFromCart(@PathVariable(name="sku") String sku) {

    }
}
