package com.inventoryservice.inventoryservice.producer;

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

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(Object obj) {
        Message<Object> message = MessageBuilder
                .withPayload(obj)
                .setHeader(KafkaHeaders.TOPIC, "demo")
                .build();
        kafkaTemplate.send(message);
        log.info(format("Sending message::%s", obj));
    }

}
