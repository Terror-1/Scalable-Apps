package com.productservice.productservice.service;

import com.externalDTOs.externalDTOs.dtos.AddToCartMessage;
import com.externalDTOs.externalDTOs.dtos.UserID;
import com.productservice.productservice.dto.ProductRequest;
import com.productservice.productservice.dto.ProductResponse;
import com.productservice.productservice.entity.Product;
import com.productservice.productservice.kafka.KafkaProducer;
import com.productservice.productservice.repository.ProductRepository;
import com.sessionservice.sessionservice.dto.CartObject;
import com.sessionservice.sessionservice.entity.CartItem;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    private final KafkaProducer kafkaProducer;
    private static final String jwtSecret = "d740b4e7547111cee19518ffef9b95645de3c346043281e52caaf7c48514e04b";

    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .sku(productRequest.getSku())
                .price((productRequest.getPrice()))
                .smallMidLargeOneSize(productRequest.getSmallMidLargeOneSize())
                .sizeNumber(productRequest.getSizeNumber())
                .quantity(productRequest.getQuantity())
                .build();
        productRepository.save(product);
    }
    public List<Product> getAllProducts() {
        return productRepository.findAll();

    }

    public ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .sku(product.getSku())
                .price(product.getPrice())
                .smallMidLargeOneSize(product.getSmallMidLargeOneSize())
                .sizeNumber(product.getSizeNumber())
                .quantity(product.getQuantity())
                .build();
    }

    public Product getProductById(int productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            // Product found, return ProductResponse
            return productOptional.get();
        } else {
            // Product not found with the given productId
            // You can throw an exception or handle it accordingly
            throw new RuntimeException("Product not found for productId: " + productId);
        }
    }
    // clothes, shoes, sunglasses, perfumes, watches, accessories
    public List<Product> getAllWomanProducts() {
        List<Product> products = productRepository.findByGender("female");
        return  products;
    }
    public List<Product> getWomanClothes() {
        List<Product> products = productRepository.findByGenderAndCategory("female", "clothes");
        return  products;
    }
    public List<Product> getWomanShoes() {
        List<Product> products = productRepository.findByGenderAndCategory("female", "shoes");
        return  products;
    }

    public List<Product> getWomanSunglasses() {
        List<Product> products = productRepository.findByGenderAndCategory("female", "sunglasses");
        return  products;
    }

    public List<Product> getWomanPerfumes() {
        List<Product> products = productRepository.findByGenderAndCategory("female", "perfumes");
        return  products;
    }

    public List<Product> getWomanWatches() {
        List<Product> products = productRepository.findByGenderAndCategory("female", "watches");
        return  products;
    }

    public List<Product> getWomanAccessories() {
        List<Product> products = productRepository.findByGenderAndCategory("female", "accessories");
        return  products;
    }
    public List<Product> getAllManProducts() {
        List<Product> products = productRepository.findByGender("male");
        return  products;
    }

    public List<Product> getManClothes() {
        List<Product> products = productRepository.findByGenderAndCategory("male", "clothes");
        return  products;
    }

    public List<Product> getManShoes() {
        List<Product> products = productRepository.findByGenderAndCategory("male", "shoes");
        return  products;
    }

    public List<Product> getManSunglasses() {
        List<Product> products = productRepository.findByGenderAndCategory("male", "sunglasses");
        return  products;
    }

    public List<Product> getManPerfumes() {
        List<Product> products = productRepository.findByGenderAndCategory("male", "perfumes");
        return  products;
    }

    public List<Product> getManWatches() {
        List<Product> products = productRepository.findByGenderAndCategory("male", "watches");
        return  products;
    }

    public List<Product> getManAccessories() {
        List<Product> products = productRepository.findByGenderAndCategory("male", "accessories");
        return  products;
    }

    public ResponseEntity<String> addToCart(HttpServletRequest request, String productIdText) {
        int productId = Integer.parseInt(productIdText);
        String token = getTokenFromCookies(request);
        String userId = getIdFromToken(token);
        Optional<Product> productOptional = productRepository.findById(productId);
        Product product;
        if (productOptional.isPresent()) {
            // Product found, return ProductResponse
            product = productOptional.get();
        } else {
            throw new RuntimeException("Product not found for productId: " + productId);
        }
        AddToCartMessage message = AddToCartMessage.builder()
                .productId(productId)
                .userId(userId)
                .name(product.getName())
                .description(product.getDescription())
                .sku(product.getSku())
                .color(product.getColor())
                .price(product.getPrice())
                .sizeNumber(product.getSizeNumber())
                .smallMidLargeOneSize(product.getSmallMidLargeOneSize())
                .build();
        kafkaProducer.addToCart(message);
        return ResponseEntity.ok("sent queued successfully to kafka");
    }

    public static String getIdFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String getTokenFromCookies(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String token = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }
        return token;
    }

    public void checkCartAndFinishOrder(CartObject msg) {
        List<CartItem> products = msg.getProducts();
        for (int i = 0; i < products.size(); i++) {
            int cartAmount = products.get(i).getQuantity();
            int rows = productRepository.updateProductQuantityBySku(products.get(i).getSku(), cartAmount);
            if (rows == 0) {
                for (int j = 0; j < i; j++) {
                    int toAdd = products.get(j).getQuantity();
                    productRepository.increaseQuantityBySku(products.get(j).getSku(), toAdd);
                }
                System.out.println("Item was not found");
            }
        }
        UserID userIdMessage = UserID.builder()
                .userId(msg.getUserId())
                .build();
        kafkaProducer.emptyCart(userIdMessage);
        System.out.println("Order Was Done Successfully");
    }
}