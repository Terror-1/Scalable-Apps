package com.productservice.productservice.controller;



import com.productservice.productservice.dto.ProductRequest;
import com.productservice.productservice.dto.ProductResponse;
import com.productservice.productservice.dto.ProductReviewDto;
import com.productservice.productservice.entity.Product;
import com.productservice.productservice.entity.Review;
import com.productservice.productservice.kafka.KafkaProducer;
import com.productservice.productservice.service.ProductService;
import com.stripe.exception.StripeException;
import jakarta.servlet.http.HttpServletRequest;
import jnr.ffi.annotations.In;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public void createProduct(@RequestBody ProductRequest productRequest) {
//        productService.createProduct(productRequest);
//    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProducts() {
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

    @PostMapping("/product/add-review")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addReview(@RequestBody ProductReviewDto productReviewDto, HttpServletRequest request) throws StripeException {
        return productService.addReview(productReviewDto, request);
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
}

