package com.example.demo.service;

import com.example.demo.dto.ThreadPoolConfig;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class ThreadPoolManager {

    private  ThreadPoolTaskExecutor executor ;

    public void registerExecutor(String serviceName, ThreadPoolTaskExecutor executor) {
        this.executor=executor;
    }

    public void updateThreadPool(ThreadPoolConfig config) {
        if (executor != null) {
            executor.setCorePoolSize(config.getCorePoolSize());
            executor.setMaxPoolSize(config.getMaxPoolSize());
            executor.setQueueCapacity(config.getQueueCapacity());
            executor.initialize();
        }
    }
}
