package com.notificationservice.notificationservice.kafka;

import com.customerservice.customerservice.dto.CustomerSessionDto;
import com.externalDTOs.externalDTOs.dtos.UserID;
import com.notificationservice.notificationservice.service.EmailSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {

    private  final EmailSender EmailSender;

    @KafkaListener(topics = "register", groupId = "register")
    public void register(CustomerSessionDto toEmail) {
        log.info(format("Consumed the customer info:: %s", toEmail));
        EmailSender.registered(toEmail.getName().split(" ")[0]);
    }

    @KafkaListener(topics = "login", groupId = "login")
    public void login(CustomerSessionDto toEmail) {
        log.info(format("Consumed the customer info:: %s", toEmail));
        EmailSender.login(toEmail.getName().split(" ")[0]);
    }
    @KafkaListener(topics = "emptyCart", groupId = "emptyCart")
    public void payment(UserID msg) {
        log.info(format("Consumed the message to notify the user:: %s", msg.toString()));
        EmailSender.payment(msg.getEmail());
    }

}
