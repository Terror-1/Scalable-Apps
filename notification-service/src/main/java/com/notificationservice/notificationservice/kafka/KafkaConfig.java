package com.notificationservice.notificationservice.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic login() {
        return TopicBuilder
                .name("login")
                .build();
    }

    @Bean
    public NewTopic register() {
        return TopicBuilder
                .name("register")
                .build();
    }
    @Bean
    public NewTopic reviewNotification() {
        return TopicBuilder
                .name("reviewNotification")
                .build();
    }


}


