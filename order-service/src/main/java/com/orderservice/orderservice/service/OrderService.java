//package com.orderservice.orderservice.service;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class OrderService {
//    @Value("${stripe.secret.key}")
//    private String stripeSecretKey;
//
//    private static final String jwtSecret = "d740b4e7547111cee19518ffef9b95645de3c346043281e52caaf7c48514e04b";
//    public ResponseEntity<String> createOrder(HttpServletRequest request) {
//        String token = getTokenFromCookies(request);
//        String userId = getIdFromToken(token);
//    }
//
//
//
//    public String getTokenFromCookies(HttpServletRequest request){
//        Cookie[] cookies = request.getCookies();
//        String token = null;
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if ("token".equals(cookie.getName())) {
//                    token = cookie.getValue();
//                    break;
//                }
//            }
//        }
//        return token;
//    }
//
//    public static String getIdFromToken(String token) {
//        return Jwts.parser()
//                .setSigningKey(jwtSecret)
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject();
//    }
//}
