package com.productservice.productservice.kafka;

import com.externalDTOs.externalDTOs.dtos.AddToCartMessage;
import com.externalDTOs.externalDTOs.dtos.UserID;
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
    private final KafkaTemplate<String, UserID> kafkaTemplate2;
    public void addToCart(AddToCartMessage msg) {
        Message<AddToCartMessage> message = MessageBuilder
                .withPayload(msg)
                .setHeader(KafkaHeaders.TOPIC, "addToCart")
                .build();
        kafkaTemplate.send( message);
        log.info(format("sending message to kafka template:: %s", message));
    }

    public void emptyCart(UserID msg) {
        Message<UserID> message = MessageBuilder
                .withPayload(msg)
                .setHeader(KafkaHeaders.TOPIC, "emptyCart")
                .build();
        kafkaTemplate2.send(message);
        log.info(format("sending message to kafka template to empty cart after successfull purchase :: %s", message));
    }
    public void reviewNotification(UserID msg) {
        Message<UserID> message = MessageBuilder
                .withPayload(msg)
                .setHeader(KafkaHeaders.TOPIC, "reviewNotification")
                .build();
        kafkaTemplate2.send(message);
        log.info(format("sending message to kafka template to send email for review notification :: %s", message));
    }
}
