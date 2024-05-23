package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {

    @Autowired
    private JavaMailSender mailSender;


    public void registered(String toEmail) {
        toEmail = toEmail + "@gmail.com";
        sendEmail(toEmail, "Registration", "You have been registered successfully");
    }

    public void login(String toEmail) {
        sendEmail(toEmail, "Login", "You have been logged in successfully");
    }
    public void order(String toEmail) {
        sendEmail(toEmail, "Order", "Your order has been placed successfully");
    }

    public void payment(String toEmail) {
        sendEmail(toEmail, "Payment", "Your payment has been processed successfully");
    }

    public void review(String toEmail) {
        sendEmail(toEmail, "Review", "Your review has been submitted successfully");
    }
    public void shipment(String toEmail) {
        sendEmail(toEmail, "Shipment", "Your order has been shipped successfully");
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
