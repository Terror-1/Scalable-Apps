package com.productservice.productservice.kafka;

import com.productservice.productservice.dto.AddToCartMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {
    private final KafkaTemplate<String, AddToCartMessage> kafkaTemplate;
    public void addToCart(AddToCartMessage msg) {
        Message<AddToCartMessage> message = MessageBuilder
                .withPayload(msg)
                .setHeader(KafkaHeaders.TOPIC, "addToCart")
                .build();
        kafkaTemplate.send( message);
        log.info(format("sending message to kafka template:: %s", message));
    }
}
