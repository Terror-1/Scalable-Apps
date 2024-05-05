//package com.orderservice.orderservice.controller;
//
//import com.orderservice.orderservice.service.OrderService;
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/order")
//@RequiredArgsConstructor
//public class OrderController {
//    private final OrderService orderService;
//
//    @PostMapping("/create-order")
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity<String> createOrder(HttpServletRequest request) {
//        return orderService.createOrder(request);
//    }
//}
