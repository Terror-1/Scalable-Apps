package com.example.demo.kafka;

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
    public NewTopic login() {
        return TopicBuilder
                .name("login")
                .build();
    }

    @Bean
    public NewTopic logout() {
        return TopicBuilder
                .name("logout")
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
}
