package com.sessionservice.sessionservice.kafka;

import com.sessionservice.sessionservice.dto.CartObject;
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
    private final KafkaTemplate<String, CartObject> kafkaTemplate;

    public void checkCartAndFinishOrder(CartObject msg) {
        Message<CartObject> message = MessageBuilder
                .withPayload(msg)
                .setHeader(KafkaHeaders.TOPIC, "checkCart")
                .build();
        kafkaTemplate.send(message);
        log.info(format("sending cart message to kafka template checkCart :: %s", message));
    }
}
