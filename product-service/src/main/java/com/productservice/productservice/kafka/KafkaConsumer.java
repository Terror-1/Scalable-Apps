package com.productservice.productservice.kafka;

import com.productservice.productservice.service.ProductService;
import com.sessionservice.sessionservice.dto.CartObject;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {
    private final ProductService productService;

    @KafkaListener(topics = "checkCart", groupId = "checkCart")
    public void checkCart(CartObject msg) {
        productService.checkCartAndFinishOrder(msg);
        log.info(format("Consumed the cart message:: %s", msg.toString()));
    }
}