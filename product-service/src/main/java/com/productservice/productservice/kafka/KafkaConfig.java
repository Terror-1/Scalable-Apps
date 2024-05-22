package com.productservice.productservice.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic addToCart() {
        return TopicBuilder
                .name("addToCart")
                .build();
    }

    @Bean
    public NewTopic checkCart() {
        return TopicBuilder
                .name("checkCart")
                .build();
    }

    @Bean
    public NewTopic emptyCart() {
        return TopicBuilder
                .name("emptyCart")
                .build();
    }

    @Bean
    public NewTopic reviewNotification() {
        return TopicBuilder
                .name("reviewNotification")
                .build();
    }

}
