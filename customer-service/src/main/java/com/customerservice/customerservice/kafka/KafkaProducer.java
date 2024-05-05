package com.customerservice.customerservice.kafka;

import com.customerservice.customerservice.dto.CustomerSessionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {
    private final KafkaTemplate<String, CustomerSessionDto> kafkaTemplate;
    public void createSession(CustomerSessionDto msg) {
        Message<CustomerSessionDto> message = MessageBuilder
                .withPayload(msg)
                .setHeader(KafkaHeaders.TOPIC, "login")
                .build();
        kafkaTemplate.send(message);
        log.info(format("sending login info to kafka template:: %s", message));
    }
}