package com.example.demo.kafka;

import com.example.demo.dto.DBConnectionConfig;
import com.example.demo.service.ProductService;
import com.example.demo.dto.CartObject;
import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {
    private final ProductService productService;

    @KafkaListener(topics = "checkCart", groupId = "checkCart")
    public void checkCart(CartObject msg) throws StripeException {
        productService.checkCartAndFinishOrder(msg);
        log.info(format("Consumed the cart message:: %s", msg.toString()));
    }
    @KafkaListener(topics = "updateDBConnection", groupId = "updateDBConnection")
    public void updateNotification(DBConnectionConfig msg) {
        log.info(format("Consumed the message to update the database connection config:: %s", msg.toString()));
        productService.updateDBConnection(msg);
    }
    @KafkaListener(topics = "freezeProductService", groupId = "freezeProductService")
    public void freezeProductService() {
        log.info(format("Consumed the message to freeze Product Service"));
        productService.freeze();
    }
    @KafkaListener(topics = "unfreezeProductService", groupId = "unfreezeProductService")
    public void unfreezeProductService() {
        log.info(format("Consumed the message to unfreeze Product Service"));
        productService.unfreeze();
    }
}