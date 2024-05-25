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

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final HikariDataSource dataSource;

//    @Autowired
//    public ProductController(HikariDataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public void createProduct(@RequestBody ProductRequest productRequest) {
//        productService.createProduct(productRequest);
//    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Product> getAllProducts() {
        return productService.getAllProducts();
    }



    @GetMapping("/product")
    @ResponseStatus(HttpStatus.OK)
    public Product getProductById(@RequestBody String productId) {
        return productService.getProductById(productId);
    }

    @GetMapping("/product/get-reviews")
    @ResponseStatus(HttpStatus.OK)
    public List<Review> getReviewsPerProduct(@RequestBody String productId) {
        return productService.getReviewsPerProduct(productId);
    }

    @GetMapping("/get-popular-products")
    @ResponseStatus(HttpStatus.OK)
    public List<PopularProducts> getPopularProducts() {
        return productService.getPopularProducts();
    }

    @GetMapping("/empty-popular-products")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> emptyPopularProducts() {
        return productService.emptyPopularProducts();
    }


    @PostMapping("/product/add-review")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addReview(@RequestBody ProductReviewDto productReviewDto, HttpServletRequest request) throws StripeException {
        return productService.addReview(productReviewDto, request);
    }

    @PostMapping("/product/delete-review")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteReview(@RequestBody String productId, HttpServletRequest request) throws StripeException {
        return productService.deleteReview(productId, request);
    }


    @GetMapping("/all-woman")
    @ResponseStatus(HttpStatus.OK)
    public List<Product>  getAllWomanProducts() {
        return productService.getAllWomanProducts();
    }
    @GetMapping("/woman-clothes")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getWomanClothes() {
        List<Product> products = productService.getWomanClothes();
        return  products;
    }
    @GetMapping("/woman-shoes")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getWomanShoes() {
        List<Product> products = productService.getWomanShoes();
        return  products;
    }
    @GetMapping("/woman-sunglasses")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getWomanSunglasses() {
        List<Product> products = productService.getWomanSunglasses();
        return  products;
    }
    @GetMapping("/woman-perfumes")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getWomanPerfume() {
        List<Product> products = productService.getWomanPerfumes();
        return  products;
    }
    @GetMapping("/woman-watches")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getWomanWatches() {
        List<Product> products = productService.getWomanWatches();
        return  products;
    }
    @GetMapping("/woman-accessories")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getWomanAccessories() {
        List<Product> products = productService.getWomanAccessories();
        return  products;
    }
    @GetMapping("/all-man")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllManProducts() {
        return productService.getAllManProducts();
    }
    @GetMapping("/man-clothes")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getManClothes() {
        List<Product> products = productService.getManClothes();
        return  products;
    }
    @GetMapping("/man-shoes")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getManShoes() {
        List<Product> products = productService.getManShoes();
        return  products;
    }
    @GetMapping("/man-sunglasses")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getManSunglasses() {
        List<Product> products = productService.getManSunglasses();
        return  products;
    }
    @GetMapping("/man-perfumes")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getManPerfume() {
        List<Product> products = productService.getManPerfumes();
        return  products;
    }
    @GetMapping("/man-watches")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getManWatches() {
        List<Product> products = productService.getManWatches();
        return  products;
    }
    @GetMapping("/man-accessories")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getManAccessories() {
        List<Product> products = productService.getManAccessories();
        return  products;
    }
    @PostMapping("/product/add-to-cart")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> addToCart(HttpServletRequest request,@RequestBody String productId) {

        return productService.addToCart(request, productId);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> searchProduct(@RequestParam String query) {
        return  productService.searchProduct(query.toLowerCase());
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

