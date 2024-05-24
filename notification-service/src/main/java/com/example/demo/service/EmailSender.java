package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class EmailSender {

    @Autowired
    private JavaMailSender mailSender;
    private static final Logger LOG = LogManager.getLogger(EmailSender.class);

    public void registered(String toEmail) {
        toEmail = toEmail + "@gmail.com";
        LOG.info("Sending registration email to {}", toEmail);
        sendEmail(toEmail, "Registration", "You have been registered successfully");
        LOG.info("Registration email sent to {}", toEmail);
    }

    public void login(String toEmail) {
        LOG.info("Sending login email to {}", toEmail);
        sendEmail(toEmail, "Login", "You have been logged in successfully");
        LOG.info("Login email sent to {}", toEmail);
    }

    public void order(String toEmail) {
        LOG.info("Sending order email to {}", toEmail);
        sendEmail(toEmail, "Order", "Your order has been placed successfully");
        LOG.info("Order email sent to {}", toEmail);
    }

    public void payment(String toEmail) {
        LOG.info("Sending payment email to {}", toEmail);
        sendEmail(toEmail, "Payment", "Your payment has been processed successfully");
        LOG.info("Payment email sent to {}", toEmail);
    }

    public void review(String toEmail) {
        LOG.info("Sending review email to {}", toEmail);
        sendEmail(toEmail, "Review", "Your review has been submitted successfully");
        LOG.info("Review email sent to {}", toEmail);
    }

    public void shipment(String toEmail) {
        LOG.info("Sending shipment email to {}", toEmail);
        sendEmail(toEmail, "Shipment", "Your order has been shipped successfully");
        LOG.info("Shipment email sent to {}", toEmail);
    }

    public void sendEmail(String to, String subject, String body) {
        LOG.debug("Preparing email to {}: Subject: {}, Body: {}", to, subject, body);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("dehige5197@lucvu.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        try {
            mailSender.send(message);
            LOG.info("Email sent to {}", to);
        } catch (Exception e) {
            LOG.error("Failed to send email to {}", to, e);
        }
    }
}
