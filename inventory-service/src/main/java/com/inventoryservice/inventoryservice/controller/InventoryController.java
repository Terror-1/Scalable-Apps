package com.inventoryservice.inventoryservice.controller;

import com.inventoryservice.inventoryservice.dto.ProductRequest;
import com.inventoryservice.inventoryservice.dto.ProductResponse;
import com.inventoryservice.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest  productRequest) {
        inventoryService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return inventoryService.getAllProducts();
    }
}
