package com.sessionservice.sessionservice.kafka;

import com.productservice.productservice.dto.AddToCartMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@Slf4j
public class KafkaConsumer {
    @KafkaListener(topics = "addToCart", groupId = "addToCart")
    public void addToCart(AddToCartMessage msg) {
        log.info(format("Consuming the message:: %s", msg));
    }
}
