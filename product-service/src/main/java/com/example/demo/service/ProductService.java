package com.example.demo.service;

import com.example.demo.controller.ProductController;
import com.example.demo.dto.*;
import com.example.demo.entity.PopularProducts;
import com.example.demo.entity.Product;
import com.example.demo.entity.Review;
import com.example.demo.kafka.KafkaProducer;
import com.example.demo.repository.PopularProductsRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.entity.CartItem;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import com.zaxxer.hikari.HikariDataSource;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    private final ProductRepository productRepository;

    private final ReviewRepository reviewRepository;

    private  final PopularProductsRepository popularProductsRepository;
    private final HikariDataSource dataSource;


    private final KafkaProducer kafkaProducer;
    private static final Logger LOG = LogManager.getLogger(ProductService.class);


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
    public Iterable<Product> getAllProducts() {
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

    public Product getProductById(String textProductId) {
        int productId = Integer.parseInt(textProductId);
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
        List<Product> products = productRepository.findByGenderAndCategory("female", "perfume");
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
        List<Product> products = productRepository.findByGenderAndCategory("male", "perfume");
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
        Product product = productOptional.get();
        if (product.getQuantity() == 0) {
            // Product found, return ProductResponse
            return new ResponseEntity<>("Item is not available in the inventory in the meantime !", HttpStatus.BAD_REQUEST);
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
        PopularProducts popularProduct = PopularProducts.builder()
                .productId(product.getId())
                .name(product.getName())
                .imageUrl(product.getImageUrl())
                .build();
        popularProductsRepository.save(popularProduct);
        return ResponseEntity.ok("sent  the product to kafka queue successfully to be put to cart !");
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

    public void checkCartAndFinishOrder(CartObject msg) throws StripeException {
        Stripe.apiKey = stripeSecretKey;
        Customer customer = Customer.retrieve(msg.getUserId());
        List<CartItem> products = msg.getProducts();
        for (int i = 0; i < products.size(); i++) {
            int cartAmount = products.get(i).getQuantity();
            int rows = productRepository.updateProductQuantityBySku(products.get(i).getSku(), cartAmount);
            System.err.println("rows affected = " + rows);
            if (rows == 0) {
                for (int j = 0; j < i; j++) {
                    int toAdd = products.get(j).getQuantity();
                    productRepository.increaseQuantityBySku(products.get(j).getSku(), toAdd);
                }
                System.out.println("Item was not found");
                return;
            }
        }
        /////////////////////////////////////////////
        PaymentIntentCreateParams.Shipping.builder()
                .setName(customer.getName())
                .setPhone(customer.getPhone())
                .setAddress(
                        PaymentIntentCreateParams.Shipping.Address.builder()
                                .setCity(customer.getShipping().getAddress().getCity())
                                .setCountry(customer.getShipping().getAddress().getCountry())
                                .setLine1(customer.getShipping().getAddress().getLine1())
                                .setPostalCode(customer.getShipping().getAddress().getPostalCode())
                                .build()
                )
                .build();
        // Create a PaymentIntent specifying the customer, currency and amount
        PaymentIntentCreateParams paymentIntentParams = PaymentIntentCreateParams.builder()
                .setCustomer(customer.getId())
                .setCurrency("usd") // Update with your currency code
                .setAmount((long) (msg.getTotalAmount() * 100)) // Convert double to long with cents conversion
                .setPaymentMethod(msg.getPaymentMethodId())
                .setShipping(
                        PaymentIntentCreateParams.Shipping.builder()
                                .setName(customer.getName())
                                .setPhone(customer.getPhone())
                                .setCarrier("carrier")
                                .setTrackingNumber("1")
                                .setAddress(
                                        PaymentIntentCreateParams.Shipping.Address.builder()
                                                .setCity(customer.getShipping().getAddress().getCity())
                                                .setCountry(customer.getShipping().getAddress().getCountry())
                                                .setLine1(customer.getShipping().getAddress().getLine1())
                                                .setPostalCode(customer.getShipping().getAddress().getPostalCode())
                                                .build()
                                )
                                .build()
                )
                .build();
        try {
            PaymentIntent paymentIntent = PaymentIntent.create(paymentIntentParams);
            UserID userIdMessage = UserID.builder()
                    .userId(msg.getUserId())
                    .email(customer.getEmail())
                    .build();
            kafkaProducer.emptyCart(userIdMessage);
            System.out.println("Order created Successfully: " + paymentIntent.getId());
        } catch (StripeException e) {
            for (int i = 0; i < products.size(); i++) {
                int toAdd = products.get(i).getQuantity();
                productRepository.increaseQuantityBySku(products.get(i).getSku(), toAdd);
            }
            System.out.println("Error creating order: " + e.getMessage());
        }
    }

    public ResponseEntity<String> addReview(ProductReviewDto productReviewDto, HttpServletRequest request) throws StripeException {
        Stripe.apiKey = stripeSecretKey;
        System.err.println("token");
        String token = getTokenFromCookies(request);
        System.err.println("this is the token" +token);
        String userId = getIdFromToken(token);
        System.err.println(userId);

        String username = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().get("username").toString();
        System.err.println(username);


        String email = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().get("email").toString();
        System.err.println(email);

//        Customer customer = Customer.retrieve(userId);
        reviewRepository.addReview(
                Integer.toString(productReviewDto.getProductId())
                , userId
                , username
                ,productReviewDto.getRating()
                , productReviewDto.getReview()
        );
        UserID userIdMessage = UserID.builder()
                .userId(userId)
                .email(email)
                .build();
        kafkaProducer.reviewNotification(userIdMessage);
        return new ResponseEntity<>("Your review was added successfully, Thank you !", HttpStatus.CREATED);
    }

    public List<Review> getReviewsPerProduct(String productId) {
        return reviewRepository.findAllByProductId(productId);
    }

    public ResponseEntity<String> deleteReview(String productId, HttpServletRequest request) {
        String token = getTokenFromCookies(request);
        String userId = getIdFromToken(token);
        Review review = reviewRepository.findByUserIdAndProductId(userId, productId);
        reviewRepository.deleteByUserIdAndProductId(userId, productId);
        if (review == null)
            return new ResponseEntity<>("There is no such review for that user on that product", HttpStatus.BAD_REQUEST);
        reviewRepository.deleteByUserIdAndProductId(userId, productId);
        return new ResponseEntity<>("Review deleted successfully !", HttpStatus.OK);
    }

    public List<PopularProducts> getPopularProducts() {
        Iterable<PopularProducts> iterable = popularProductsRepository.findAll();

        // Convert the Iterable to a Stream using StreamSupport
        Stream<PopularProducts> stream = StreamSupport.stream(iterable.spliterator(), false);
        // Collect the Stream elements into a List using Collectors.toList()
        List<PopularProducts> productList = stream.collect(Collectors.toList());
        return productList;
    }

    public ResponseEntity<String> emptyPopularProducts() {
        popularProductsRepository.deleteAll();
        return  new ResponseEntity<>("The cache is now empty", HttpStatus.OK);
    }

    public List<Product> searchProduct(String name) {
        return productRepository.searchByNameOrSkuOrDescriptionContaining(name);
    }
    public String updateDBConnection(DBConnectionConfig config) {
        LOG.info("idle before"+dataSource.getMinimumIdle());
        dataSource.setMinimumIdle(config.getMinimumIdle());
        LOG.info("idle after"+dataSource.getMinimumIdle());
        LOG.info("maxpool before"+dataSource.getMaximumPoolSize());
        dataSource.setMaximumPoolSize(config.getMaximumPoolSize());
        LOG.info("maxpool after"+dataSource.getMaximumPoolSize());
        return "Updated pool size settings";
    }
}