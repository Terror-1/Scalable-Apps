package com.example.demo.kafka;

import com.example.demo.dto.AddToCartMessage;
import com.example.demo.dto.CustomerSessionDto;

import com.example.demo.dto.ThreadPoolConfig;
import com.example.demo.dto.UserID;
import com.example.demo.service.SessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {
    private final SessionService sessionService;
    @KafkaListener(topics = "addToCart", groupId = "addToCart")
    public void addToCart(AddToCartMessage msg) {
        log.info(format("Consumed the message:: %s", msg.toString()));
        sessionService.addToCart(msg);
    }

    @KafkaListener(topics = "login", groupId = "login")
    public void login(CustomerSessionDto msg) {
        log.info(format("Consumed the customer session info:: %s", msg.toString()));
        sessionService.createSession(msg);
    }

    @KafkaListener(topics = "logout", groupId = "logout")
    public void logout(CustomerSessionDto msg) {
        log.info(format("consumed the logout session destroy request:: %s", msg.toString()));
        sessionService.destroySession(msg);
    }

    @KafkaListener(topics = "emptyCart", groupId = "emptyCart")
    public void emptyCart(UserID msg) {
        log.info(format("Consumed the message to empty the cart:: %s", msg.toString()));
        sessionService.emptyCartAfterPurchase(msg);
    }
    @KafkaListener(topics = "updateSessionConfig", groupId = "updateSessionConfig")
    public void updateSessionConfig(ThreadPoolConfig msg) {
        log.info(format("Consumed the message to empty the cart:: %s", msg.toString()));
        sessionService.updateThreadPool(msg);
    }
}
