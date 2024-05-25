package com.example.demo.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic updateProduct() {
        return TopicBuilder
                .name("updateProductConfig")
                .build();
    }
    @Bean
    public NewTopic updateCustomer() {
        return TopicBuilder
                .name("updateCustomerConfig")
                .build();
    }
    @Bean
    public NewTopic updateSession() {
        return TopicBuilder
                .name("updateSessionConfig")
                .build();
    }
    @Bean
    public NewTopic updateNotification() {
        return TopicBuilder
                .name("updateNotificationConfig")
                .build();
    }
    @Bean
    public NewTopic updateDBConnection() {
        return TopicBuilder
                .name("updateDBConnection")
                .build();
    }

}
