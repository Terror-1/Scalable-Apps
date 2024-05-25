package com.example.demo.configuration;

import com.example.demo.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class SessionServiceConfig{
    @Autowired
    private SessionService sessionService;
    @Bean(name = "sessionTaskExecutor")
    public ThreadPoolTaskExecutor sessionTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(50);
        executor.setThreadNamePrefix("SessionService-");
        executor.initialize();
        sessionService.registerExecutor("session-service", executor);
        return executor;
    }
}
