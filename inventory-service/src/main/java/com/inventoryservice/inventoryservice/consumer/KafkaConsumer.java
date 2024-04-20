package com.inventoryservice.inventoryservice.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@Slf4j
public class KafkaConsumer {
    @KafkaListener(topics = "demo", groupId = "inventory")
    public void consumeMsg(Object msg) {
        log.info(format("consuming the message:: %s", msg));
    }
}
