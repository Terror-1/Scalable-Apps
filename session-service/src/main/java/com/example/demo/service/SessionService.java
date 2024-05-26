package com.example.demo.service;


import com.example.demo.dto.*;
import com.example.demo.entity.Session;
import com.example.demo.kafka.KafkaProducer;
import com.example.demo.entity.CartItem;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.SessionRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentMethodCollection;
import com.stripe.param.CustomerListPaymentMethodsParams;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class SessionService {
    private final SessionRepository sessionRepository;
    private final CartItemRepository cartItemRepository;
    private final KafkaProducer kafkaProducer;
    private static final String jwtSecret = "d740b4e7547111cee19518ffef9b95645de3c346043281e52caaf7c48514e04b";
    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

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

    public CartObject viewCart(HttpServletRequest request) throws StripeException {
        Stripe.apiKey = stripeSecretKey;
        String token = getTokenFromCookies(request);
        String userId = getIdFromToken(token);
        Customer customer = Customer.retrieve(userId);
        String defaultPaymentMethodId = customer.getInvoiceSettings().getDefaultPaymentMethod();
        List<CartItem> cartItems= cartItemRepository.findAllByUserId(userId);
        double totalAmount = 0;
        for (int i = 0; i < (int) cartItems.size(); i++) {
            totalAmount += cartItems.get(i).getQuantity() * cartItems.get(i).getItemPrice();
        }
        CartObject cartObject =  CartObject.builder()
                .totalAmount(totalAmount)
                .userId(userId)
                .products(cartItems)
                .paymentMethodId(defaultPaymentMethodId)
                .build();
        return cartObject;
    }
    public ResponseEntity<String> emptyCart(HttpServletRequest request) {
        String token = getTokenFromCookies(request);
        String userId = getIdFromToken(token);
        cartItemRepository.deleteAllByUserId(userId);
        return new ResponseEntity<>("Cart is now empty", HttpStatus.OK);
    }

    public ResponseEntity<String> removeItem(HttpServletRequest request, String itemId) {
        String token = getTokenFromCookies(request);
        String userId = getIdFromToken(token);
        Optional<CartItem> cartItemOptional = cartItemRepository.findByItemIdAndUserId(itemId, userId);
        if (cartItemOptional.isEmpty()) {
            return new ResponseEntity<>("Can't remove the item because it is not in your cart !", HttpStatus.BAD_REQUEST);
        }
        CartItem existingCartItem = cartItemOptional.get();
        if (existingCartItem.getQuantity() == 1) {
            cartItemRepository.deleteCartItemByUserIdAndItemId(userId, itemId);
        } else {
            existingCartItem.setQuantity(existingCartItem.getQuantity() - 1);
            cartItemRepository.save(existingCartItem);
        }
        return new ResponseEntity<>("Removed the item from the cart !", HttpStatus.OK);
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

    public ResponseEntity<String> createOrder(HttpServletRequest request) throws StripeException {
        Stripe.apiKey = stripeSecretKey;
        String token = getTokenFromCookies(request);
        String userId = getIdFromToken(token);
        Customer customer = Customer.retrieve(userId);
        CustomerListPaymentMethodsParams params =
                CustomerListPaymentMethodsParams.builder().build();
        PaymentMethodCollection paymentMethods = customer.listPaymentMethods(params);
        if (paymentMethods.getData().isEmpty()) {
            return new ResponseEntity<>("Error please add a payment method first !", HttpStatus.BAD_REQUEST);
        }
        String defaultPaymentMethodId = customer.getInvoiceSettings().getDefaultPaymentMethod();
        if (defaultPaymentMethodId == null)
            return new ResponseEntity<>("You have no default payment method, please set one !", HttpStatus.BAD_REQUEST);
        if (defaultPaymentMethodId.equals(""))
            return new ResponseEntity<>("You have no default payment method, please set one !", HttpStatus.BAD_REQUEST);
        if (customer.getShipping() == null) {
            return new ResponseEntity<>("Error please add a shipping address first !", HttpStatus.BAD_REQUEST);
        }
        List<CartItem> cartItems = cartItemRepository.findAllByUserId(userId);
        if (cartItems.isEmpty()) {
            return new ResponseEntity<>("Error please add products to your cart first !", HttpStatus.BAD_REQUEST);
        }
        double totalAmount = 0;
        for (CartItem cartItem: cartItems) totalAmount += cartItem.getItemPrice() * cartItem.getQuantity();
         CartObject cartObject = CartObject.builder()
                 .paymentMethodId(defaultPaymentMethodId)
                 .products(cartItems)
                 .userId(userId)
                 .totalAmount(totalAmount)
                 .build();
         kafkaProducer.checkCartAndFinishOrder(cartObject);
         return new ResponseEntity<>("sent your cart to queue to continue processing the payment", HttpStatus.CREATED);
    }

    public void emptyCartAfterPurchase(UserID msg) {
        String userId = msg.getUserId();
        cartItemRepository.deleteAllByUserId(userId);
    }

    public void destroySession(CustomerSessionDto msg) {
        String userId = msg.getStripeId();
        cartItemRepository.deleteAllByUserId(userId);
        sessionRepository.deleteByUserId(userId);
    }
    private ThreadPoolTaskExecutor executor ;
    private static final Logger LOG = LogManager.getLogger(SessionService.class);


    public void registerExecutor(String serviceName, ThreadPoolTaskExecutor executor) {
        this.executor=executor;
    }

    public void updateThreadPool(ThreadPoolConfig config) {
        LOG.info("updating thread pool configuration for session service");


        if (executor != null) {
            LOG.info("core pool size was: "+executor.getCorePoolSize());
            LOG.info("max pool size was: "+executor.getMaxPoolSize());
            LOG.info("Queue Capacity was: "+executor.getQueueCapacity());
            executor.setCorePoolSize(config.getCorePoolSize());
            executor.setMaxPoolSize(config.getMaxPoolSize());
            executor.setQueueCapacity(config.getQueueCapacity());
            executor.initialize();
            LOG.info("core pool size now is: "+executor.getCorePoolSize());
            LOG.info("max pool size now is: "+executor.getMaxPoolSize());
            LOG.info("Queue Capacity now is: "+executor.getQueueCapacity());
        }
        else{
            LOG.info("session service executor is null");
        }



    }
}
