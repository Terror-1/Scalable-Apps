package com.example.demo.kafka;

import com.example.demo.dto.CustomerSessionDto;

import com.example.demo.dto.ThreadPoolConfig;
import com.example.demo.dto.UserID;
import com.example.demo.service.ThreadPoolManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {

    private  final com.example.demo.service.EmailSender EmailSender;

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

    @KafkaListener(topics = "reviewNotification", groupId = "reviewNotification")
    public void reviewNotification(UserID msg) {
        log.info(format("Consumed the message to notify the user:: %s", msg.toString()));
        EmailSender.review(msg.getEmail());
    }
    @KafkaListener(topics = "updateNotificationConfig", groupId = "updateNotificationConfig")
    public void updateNotification(ThreadPoolConfig msg) {
        log.info(format("Consumed the message to update the notifcation config:: %s", msg.toString()));
        EmailSender.updateThreadPool(msg);
    }

}
