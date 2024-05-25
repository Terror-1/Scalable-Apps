package com.example.demo.configuration;

import com.example.demo.service.EmailSender;
import com.example.demo.service.ThreadPoolManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class NotificationServiceConfig {
    @Autowired
    private EmailSender emailSender;
    @Bean(name = "notificationTaskExecutor")
    public ThreadPoolTaskExecutor productTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(50);
        executor.setThreadNamePrefix("NotificationService-");
        executor.initialize();
        emailSender.registerExecutor("notification-service", executor);
        return executor;
    }
}
