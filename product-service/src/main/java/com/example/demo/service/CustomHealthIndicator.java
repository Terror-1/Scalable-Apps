package com.example.demo.service;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    private boolean up = true;

    public void setHealth(boolean up) {
        this.up = up;
    }

    @Override
    public Health health() {
        if (up) {
            return Health.up().build();
        } else {
            return Health.down().withDetail("Error", "Service is frozen").build();
        }
    }
}
