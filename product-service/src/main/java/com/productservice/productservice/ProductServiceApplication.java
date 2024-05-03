package com.productservice.productservice;

import com.productservice.productservice.entity.Product;
import com.productservice.productservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.nio.file.Path;
import java.util.Optional;

@SpringBootApplication
public class ProductServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}
	@Bean
	public CommandLineRunner loadData(ProductRepository productRepository) {
		return args -> {
			Product product1 = new Product();
			product1.setSku("iphone_14_black");
			product1.setPrice(1200);
			product1.setSmallMidLargeOneSize("-");
			product1.setSizeNumber(-1);
			product1.setQuantity(50);
			product1.setGender("-");
			product1.setDescription("elfkhebvfeklvbb vwejbcjwdfc ewlfjkhboewufb ewufhbewou;bfj");
			productRepository.save(product1);
			Optional<Product> products1 = productRepository.findBySku(product1.getSku());
			int product1Id = product1.getId();
		};
	}
}
