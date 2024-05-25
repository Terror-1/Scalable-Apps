package com.example.demo.service;


import com.example.demo.dto.ThreadPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executor;

@Service
public class EmailSender {

    @Autowired
    private JavaMailSender mailSender;
    private ThreadPoolTaskExecutor executor ;

    public void registerExecutor(String serviceName, ThreadPoolTaskExecutor executor) {
        this.executor=executor;
    }

    public void updateThreadPool(ThreadPoolConfig config) {
        System.out.println("executor "+executor);
        System.out.println("executor pool size "+executor.getCorePoolSize());
        System.out.println("config pool size "+config.getCorePoolSize());
        if (executor != null) {
            executor.setCorePoolSize(config.getCorePoolSize());
            executor.setMaxPoolSize(config.getMaxPoolSize());
            executor.setQueueCapacity(config.getQueueCapacity());
            executor.initialize();
        }
        System.out.println("executor pool size "+executor.getCorePoolSize());

    }

    public void registered(String toEmail) {
        executor.execute(()->{
            sendEmail(toEmail, "Registration", "You have been registered successfully");
        });

    }

    public void login(String toEmail) {
        executor.execute(()->{
        sendEmail(toEmail, "Login", "You have been logged in successfully");});
    }
    public void order(String toEmail) {
        executor.execute(()->{
        sendEmail(toEmail, "Order", "Your order has been placed successfully");});
    }

    public void payment(String toEmail) {
        executor.execute(()->{
        sendEmail(toEmail, "Payment", "Your payment has been processed successfully");});
    }

    public void review(String toEmail) {
        executor.execute(()->{
        sendEmail(toEmail, "Review", "Your review has been submitted successfully");});
    }
    public void shipment(String toEmail) {
        executor.execute(()->{
        sendEmail(toEmail, "Shipment", "Your order has been shipped successfully");});
    }



    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("dehige5197@lucvu.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
        System.out.println("Email sent to " + to);
    }
}
