package com.sessionservice.sessionservice.service;


import com.customerservice.customerservice.dto.CustomerSessionDto;
import com.externalDTOs.externalDTOs.dtos.AddToCartMessage;
import com.externalDTOs.externalDTOs.dtos.UserID;
import com.sessionservice.sessionservice.dto.CartObject;
import com.sessionservice.sessionservice.entity.CartItem;
import com.sessionservice.sessionservice.entity.CartItemKey;
import com.sessionservice.sessionservice.entity.Session;
import com.sessionservice.sessionservice.kafka.KafkaProducer;
import com.sessionservice.sessionservice.repository.CartItemRepository;
import com.sessionservice.sessionservice.repository.SessionRepository;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.type.ConvertedBasicArrayType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class SessionService {
    private final SessionRepository sessionRepository;
    private final CartItemRepository cartItemRepository;
    private final KafkaProducer kafkaProducer;
    private static final String jwtSecret = "d740b4e7547111cee19518ffef9b95645de3c346043281e52caaf7c48514e04b";

    public ResponseEntity<String> addToCart(AddToCartMessage msg) {
        String itemId = msg.getProductId().toString();
        String userId = msg.getUserId();
         Optional<CartItem> cartItemOptional = cartItemRepository.findByItemIdAndUserId(itemId, userId);
        if (cartItemOptional.isPresent()) {
            // Cart item exists, increment its quantity
            CartItem existingCartItem = cartItemOptional.get();
            existingCartItem.setQuantity(existingCartItem.getQuantity() + 1);
            cartItemRepository.save(existingCartItem);
        } else {
            // Cart item does not exist, create a new CartItem and save it
            CartItem newCartItem = CartItem.builder()
                    .userID(userId)
                    .itemId(itemId)
                    .color(msg.getColor())
                    .itemPrice(msg.getPrice())
                    .name(msg.getName())
                    .sku(msg.getSku())
                    .quantity(1)
                    .sizeNumber(msg.getSizeNumber())
                    .smallMidLargeOneSize(msg.getSmallMidLargeOneSize())
                    .build();
            cartItemRepository.save(newCartItem);
        }
        return new ResponseEntity<>("added product to cart!", HttpStatus.CREATED);
    }

    public CartObject viewCart(HttpServletRequest request) {
        String token = getTokenFromCookies(request);
        String userId = getIdFromToken(token);
        List<CartItem> cartItems= cartItemRepository.findAllByUserId(userId);
        double totalAmount = 0;
        for (int i = 0; i < (int) cartItems.size(); i++) {
            totalAmount += cartItems.get(i).getQuantity() * cartItems.get(i).getItemPrice();
        }
        return CartObject.builder()
                .totalAmount(totalAmount)
                .userId(userId)
                .products(cartItems)
                .build();
    }
    public void emptyCart(HttpServletRequest request) {
        String token = getTokenFromCookies(request);
        String userId = getIdFromToken(token);
        cartItemRepository.deleteAllByUserId(userId);
    }

    public void removeItem(HttpServletRequest request, String itemId) {
        String token = getTokenFromCookies(request);
        String userId = getIdFromToken(token);
        cartItemRepository.decrementQuantityIfGreaterThanOne(userId, itemId);
    }

    public void createSession(CustomerSessionDto msg) {
        Session session = Session.builder()
                .userId(msg.getStripeId())
                .name(msg.getName())
                .build();
        sessionRepository.save(session);
    }

    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    public static String getIdFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String getTokenFromCookies(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String token = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }
        return token;
    }

    public ResponseEntity<String> createOrder(HttpServletRequest request) {
        String token = getTokenFromCookies(request);
        String userId = getIdFromToken(token);
        List<CartItem> cartItems = cartItemRepository.findAllByUserId(userId);
         CartObject cartObject = CartObject.builder()
                 .products(cartItems)
                 .userId(userId)
                 .totalAmount(0.0)
                 .build();
         kafkaProducer.checkCartAndFinishOrder(cartObject);
         return new ResponseEntity<>("sent your cart to queue to continue processing the payment", HttpStatus.CREATED);
    }

    public void emptyCartAfterPurchase(UserID msg) {
        String userId = msg.getUserId();
        cartItemRepository.deleteAllByUserId(userId);
    }
}
