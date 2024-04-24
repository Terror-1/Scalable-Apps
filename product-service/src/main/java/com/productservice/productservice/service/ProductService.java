package com.productservice.productservice.service;

import com.productservice.productservice.dto.ProductRequest;
import com.productservice.productservice.dto.ProductResponse;
import com.productservice.productservice.entity.Product;
import com.productservice.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

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
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).toList();
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
    }

    public List<Product> getWomanSunglasses() {
        List<Product> products = productRepository.findByGenderAndCategory("female", "sunglasses");
    }

    public List<Product> getWomanPerfumes() {
        List<Product> products = productRepository.findByGenderAndCategory("female", "perfumes");
    }

    public List<Product> getWomanWatches() {
        List<Product> products = productRepository.findByGenderAndCategory("female", "watches");
    }

    public List<Product> getWomanAccessories() {
        List<Product> products = productRepository.findByGenderAndCategory("female", "accessories");
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
}