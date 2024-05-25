package com.example.demo.controller;



import com.example.demo.entity.PopularProducts;
import com.example.demo.entity.Product;
import com.example.demo.entity.Review;
import com.example.demo.service.ProductService;
import com.example.demo.dto.ProductReviewDto;
import com.stripe.exception.StripeException;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private static final Logger LOG = LogManager.getLogger(ProductController.class);
    private final HikariDataSource dataSource;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Product> getAllProducts() {
        LOG.info("Received request to get all products");
        Iterable<Product> products = productService.getAllProducts();
        LOG.info("Returning {} products", ((List<Product>) products).size());
        return products;
    }



    @GetMapping("/product")
    @ResponseStatus(HttpStatus.OK)
    public Product getProductById(@RequestBody String productId) {
        LOG.info("Received request to get product by ID: {}", productId);
        Product product = productService.getProductById(productId);
        LOG.info("Returning product: {}", product);
        return product;
    }

    @GetMapping("/product/get-reviews")
    @ResponseStatus(HttpStatus.OK)
    public List<Review> getReviewsPerProduct(@RequestBody String productId) {
        LOG.info("Received request to get reviews for product ID: {}", productId);
        List<Review> reviews = productService.getReviewsPerProduct(productId);
        LOG.info("Returning {} reviews for product ID: {}", reviews.size(), productId);
        return reviews;
    }

    @GetMapping("/get-popular-products")
    @ResponseStatus(HttpStatus.OK)
    public List<PopularProducts> getPopularProducts() {
        LOG.info("Received request to get popular products");
        List<PopularProducts> popularProducts = productService.getPopularProducts();
        LOG.info("Returning {} popular products", popularProducts.size());
        return popularProducts;
    }

    @GetMapping("/empty-popular-products")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> emptyPopularProducts() {
        LOG.info("Received request to empty popular products");
        ResponseEntity<String> response = productService.emptyPopularProducts();
        LOG.info("Empty popular products response: {}", response);
        return response;
    }


    @PostMapping("/product/add-review")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addReview(@RequestBody ProductReviewDto productReviewDto, HttpServletRequest request) throws StripeException {
        LOG.info("Received request to add review for product ID: {}", productReviewDto.getProductId());
        ResponseEntity<String> response = productService.addReview(productReviewDto, request);
        LOG.info("Add review response: {}", response);
        return response;
    }

    @PostMapping("/product/delete-review")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteReview(@RequestBody String productId, HttpServletRequest request) throws StripeException {
        LOG.info("Received request to delete review for product ID: {}", productId);
        ResponseEntity<String> response = productService.deleteReview(productId, request);
        LOG.info("Delete review response: {}", response);
        return response;
    }

    @GetMapping("/all-woman")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllWomanProducts() {
        LOG.info("Received request to get all woman products");
        List<Product> products = productService.getAllWomanProducts();
        LOG.info("Returning {} woman products", products.size());
        return products;
    }
    @GetMapping("/woman-clothes")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getWomanClothes() {
        LOG.info("Received request to get woman clothes");
        List<Product> products = productService.getWomanClothes();
        LOG.info("Returning {} woman clothes", products.size());
        return products;
    }
    @GetMapping("/woman-shoes")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getWomanShoes() {
        LOG.info("Received request to get woman shoes");
        List<Product> products = productService.getWomanShoes();
        LOG.info("Returning {} woman shoes", products.size());
        return products;
    }
    @GetMapping("/woman-sunglasses")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getWomanSunglasses() {
        LOG.info("Received request to get woman sunglasses");
        List<Product> products = productService.getWomanSunglasses();
        LOG.info("Returning {} woman sunglasses", products.size());
        return products;
    }
    @GetMapping("/woman-perfumes")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getWomanPerfume() {
        LOG.info("Received request to get woman perfumes");
        List<Product> products = productService.getWomanPerfumes();
        LOG.info("Returning {} woman perfumes", products.size());
        return products;
    }
    @GetMapping("/woman-watches")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getWomanWatches() {
        LOG.info("Received request to get woman watches");
        List<Product> products = productService.getWomanWatches();
        LOG.info("Returning {} woman watches", products.size());
        return products;
    }
    @GetMapping("/woman-accessories")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getWomanAccessories() {
        LOG.info("Received request to get woman accessories");
        List<Product> products = productService.getWomanAccessories();
        LOG.info("Returning {} woman accessories", products.size());
        return products;
    }
    @GetMapping("/all-man")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllManProducts() {
        LOG.info("Received request to get all man products");
        List<Product> products = productService.getAllManProducts();
        LOG.info("Returning {} man products", products.size());
        return products;
    }
    @GetMapping("/man-clothes")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getManClothes() {
        LOG.info("Received request to get man clothes");
        List<Product> products = productService.getManClothes();
        LOG.info("Returning {} man clothes", products.size());
        return products;
    }
    @GetMapping("/man-shoes")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getManShoes() {
        LOG.info("Received request to get man shoes");
        List<Product> products = productService.getManShoes();
        LOG.info("Returning {} man shoes", products.size());
        return products;
    }
    @GetMapping("/man-sunglasses")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getManSunglasses() {
        LOG.info("Received request to get man sunglasses");
        List<Product> products = productService.getManSunglasses();
        LOG.info("Returning {} man sunglasses", products.size());
        return products;
    }
    @GetMapping("/man-perfumes")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getManPerfume() {
        LOG.info("Received request to get man perfumes");
        List<Product> products = productService.getManPerfumes();
        LOG.info("Returning {} man perfumes", products.size());
        return products;
    }
    @GetMapping("/man-watches")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getManWatches() {
        LOG.info("Received request to get man watches");
        List<Product> products = productService.getManWatches();
        LOG.info("Returning {} man watches", products.size());
        return products;
    }

    @GetMapping("/man-accessories")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getManAccessories() {
        LOG.info("Received request to get man accessories");
        List<Product> products = productService.getManAccessories();
        LOG.info("Returning {} man accessories", products.size());
        return products;
    }
    @PostMapping("/product/add-to-cart")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> addToCart(HttpServletRequest request, @RequestBody String productId) {
        LOG.info("Received request to add product ID {} to cart", productId);
        ResponseEntity<String> response = productService.addToCart(request, productId);
        LOG.info("Add to cart response: {}", response);
        return response;
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> searchProduct(@RequestParam String query) {
        LOG.info("Received request to search for products with query: {}", query);
        List<Product> products = productService.searchProduct(query.toLowerCase());
        LOG.info("Returning {} products for search query: {}", products.size(), query);
        return products;
    }
    @GetMapping("/get-pool-size")
    public String updatePoolSize() throws SQLException {
        System.out.println(dataSource.getHikariPoolMXBean());
        System.out.println( dataSource.getConnection());
        System.out.println(dataSource.getMaximumPoolSize());
        System.out.println(dataSource.getMinimumIdle());
//        System.out.println(dataSource.connec);
        return "Updated pool size settings";
    }
    @PostMapping("/update-pool-size")
    public String updatePoolSize(@RequestParam int minimumIdle, @RequestParam int maximumPoolSize) {
        System.out.println("idle before"+dataSource.getMinimumIdle());
        dataSource.setMinimumIdle(minimumIdle);
        System.out.println("idle after"+dataSource.getMinimumIdle());
        System.out.println("maxpool before"+dataSource.getMaximumPoolSize());
        dataSource.setMaximumPoolSize(maximumPoolSize);
        System.out.println("maxpool after"+dataSource.getMaximumPoolSize());
        return "Updated pool size settings";
    }

}

