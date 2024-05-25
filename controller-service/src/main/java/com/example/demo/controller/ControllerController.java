package com.example.demo.controller;

import com.example.demo.dto.DBConnectionConfig;
import com.example.demo.dto.ThreadPoolConfig;
import com.example.demo.kafka.KafkaProducer;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/controller")
@RequiredArgsConstructor
public class ControllerController {
    private final KafkaProducer kafkaProducer;
    @PostMapping("/update-product")
    public ResponseEntity<String> updateProduct(@RequestBody ThreadPoolConfig config) {
        kafkaProducer.updateProduct(config);

        return ResponseEntity.ok("Thread pool updated successfully");
    }
    @PostMapping("/update-customer")
    public ResponseEntity<String> updateCustomer(@RequestBody ThreadPoolConfig config) {
        kafkaProducer.updateCustomer(config);

        return ResponseEntity.ok("Thread pool updated successfully");
    }
    @PostMapping("/update-session")
    public ResponseEntity<String> updateSession(@RequestBody ThreadPoolConfig config) {
        kafkaProducer.updateSession(config);

        return ResponseEntity.ok("Thread pool updated successfully");
    }
    @PostMapping("/update-notification")
    public ResponseEntity<String> updateNotification(@RequestBody ThreadPoolConfig config) {
        kafkaProducer.updateNotification(config);
        return ResponseEntity.ok("Thread pool updated successfully");
    }
    @PostMapping("/update-dbconnection")
    public ResponseEntity<String> updateNotification(@RequestBody DBConnectionConfig config) {
        kafkaProducer.updateDBConnection(config);
        return ResponseEntity.ok("DataBase config updated successfully");
    }
    @PostMapping("/freeze-product")
    public ResponseEntity<String> freezeProduct() {
        kafkaProducer.freezeProductService();
        return ResponseEntity.ok("DataBase config updated successfully");
    }


}



