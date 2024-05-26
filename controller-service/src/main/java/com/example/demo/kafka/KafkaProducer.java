package com.example.demo.kafka;

import com.example.demo.dto.DBConnectionConfig;
import com.example.demo.dto.ThreadPoolConfig;
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
    private final KafkaTemplate<String, ThreadPoolConfig> kafkaTemplate;
    private final KafkaTemplate<String, DBConnectionConfig> kafkaTemplate2;
    private final KafkaTemplate<String, String> kafkaTemplate3;
    public void updateProduct(ThreadPoolConfig msg) {
        Message<ThreadPoolConfig> message = MessageBuilder
                .withPayload(msg)
                .setHeader(KafkaHeaders.TOPIC, "updateProductConfig")
                .build();
        kafkaTemplate.send( message);
        log.info(format("sending product config update message to kafka template:: %s", message));
    }
    public void updateCustomer(ThreadPoolConfig msg) {
        Message<ThreadPoolConfig> message = MessageBuilder
                .withPayload(msg)
                .setHeader(KafkaHeaders.TOPIC, "updateCustomerConfig")
                .build();
        kafkaTemplate.send( message);
        log.info(format("sending customer config update message to kafka template:: %s", message));
    }
    public void updateSession(ThreadPoolConfig msg) {
        Message<ThreadPoolConfig> message = MessageBuilder
                .withPayload(msg)
                .setHeader(KafkaHeaders.TOPIC, "updateSessionConfig")
                .build();
        kafkaTemplate.send( message);
        log.info(format("sending session config update message to kafka template:: %s", message));
    }
    public void updateNotification(ThreadPoolConfig msg) {
        Message<ThreadPoolConfig> message = MessageBuilder
                .withPayload(msg)
                .setHeader(KafkaHeaders.TOPIC, "updateNotificationConfig")
                .build();
        kafkaTemplate.send( message);
        log.info(format("sending notification config update message to kafka template:: %s", message));
    }
    public void updateDBConnection(DBConnectionConfig msg) {
        Message<DBConnectionConfig> message = MessageBuilder
                .withPayload(msg)
                .setHeader(KafkaHeaders.TOPIC, "updateDBConnection")
                .build();
        kafkaTemplate2.send( message);
        log.info(format("sending DB connection update message to kafka template:: %s", message));
    }
    public void freezeProductService() {
        Message<String> message = MessageBuilder
                .withPayload("freezeProductService")
                .setHeader(KafkaHeaders.TOPIC, "freezeProductService")
                .build();
        kafkaTemplate3.send( message);
        log.info(format("sending freeze product service message to kafka template:: %s", message));
    }
    public void unfreezeProductService() {
        Message<String> message = MessageBuilder
                .withPayload("unfreezeProductService")
                .setHeader(KafkaHeaders.TOPIC, "unfreezeProductService")
                .build();
        kafkaTemplate3.send( message);
        log.info(format("sending unfreeze product service message to kafka template:: %s", message));
    }
}
