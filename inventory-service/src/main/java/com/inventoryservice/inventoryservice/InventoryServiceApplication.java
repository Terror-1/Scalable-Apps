package com.inventoryservice.inventoryservice;

import com.inventoryservice.inventoryservice.entity.ProductItem;
import com.inventoryservice.inventoryservice.repository.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.nio.file.Path;

@SpringBootApplication
public class InventoryServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }
    @Bean
    public CommandLineRunner loadData(ProductItemRepository productItemRepository) {
        return args -> {
            ProductItem product1 = new ProductItem();
            product1.setSku("iphone_14_black");
            product1.setPrice(1200);
            product1.setSmallMidLargeOneSize("-");
            product1.setSizeNumber(-1);
            product1.setQuantity(100);
            productItemRepository.save(product1);
        };

    }
}

