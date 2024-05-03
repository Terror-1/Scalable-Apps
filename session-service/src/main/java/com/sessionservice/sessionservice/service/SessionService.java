package com.sessionservice.sessionservice.service;

import com.productservice.productservice.dto.AddToCartMessage;
import com.sessionservice.sessionservice.dto.CartObject;
import com.sessionservice.sessionservice.entity.CartItem;
import com.sessionservice.sessionservice.entity.CartItemKey;
import com.sessionservice.sessionservice.repository.CartItemRepository;
import com.sessionservice.sessionservice.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.type.ConvertedBasicArrayType;
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

    public void addToCart(AddToCartMessage msg) {
        CartItemKey cartItemKey = CartItemKey.builder()
                .itemId(msg.getProductId().toString())
                .token(msg.getUserToken())
                .build();
         Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemKey);
        if (cartItemOptional.isPresent()) {
            // Cart item exists, increment its quantity
            CartItem existingCartItem = cartItemOptional.get();
            existingCartItem.setQuantity(existingCartItem.getQuantity() + 1);
            cartItemRepository.save(existingCartItem);
        } else {
            // Cart item does not exist, create a new CartItem and save it
            CartItem newCartItem = CartItem.builder()
                    .cartItemKey(cartItemKey)
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
    }

    public CartObject viewCart(String token) {
        List<CartItem> cartItems= cartItemRepository.findAllByToken(token);
        double totalAmount = 0;
        for (int i = 0; i < (int) cartItems.size(); i++) {
            totalAmount += cartItems.get(i).getQuantity() * cartItems.get(i).getItemPrice();
        }
        return CartObject.builder()
                .totalAmount(totalAmount)
                .products(cartItems)
                .build();
    }
    public void emptyCart(String token) {
        cartItemRepository.deleteAllByToken(token);
    }

    public void removeItem(String token, String itemId) {
        boolean deleted = cartItemRepository.deleteIfQuantityIsOne(token, itemId);
        if (!deleted) cartItemRepository.decrementQuantityIfGreaterThanOne(token, itemId);
    }

    public ResponseEntity<String> purchase(String token) {

    }
}
