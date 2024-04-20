package com.inventoryservice.inventoryservice.service;
import java.util.*;
import com.inventoryservice.inventoryservice.dto.ProductRequest;
import com.inventoryservice.inventoryservice.dto.ProductResponse;
import com.inventoryservice.inventoryservice.entity.ItemSize;
import com.inventoryservice.inventoryservice.entity.ProductItem;
import com.inventoryservice.inventoryservice.repository.ProductItemRepository;
import com.inventoryservice.inventoryservice.repository.ItemSizeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {
    private final ItemSizeRepository itemSizeRepository;
    private final ProductItemRepository productItemRepository;
    public void createProduct(ProductRequest productRequest) {
        ProductItem productItem = ProductItem.builder()
                .sku(productRequest.getSku())
                .price((productRequest.getPrice()))
                .smallMidLargeOneSize(productRequest.getSmallMidLargeOneSize())
                .sizeNumber(productRequest.getSizeNumber())
                .quantity(productRequest.getQuantity())
                .build();
        productItemRepository.save(productItem);
    }
//    public void createItemSize(long productId, String sku, boolean sizes, String smallMidLargeOneSize, int quantity, int sizeNumber) {
//        ProductItem productItem1 = ProductItem.builder()
//                .id(productId)
//                .build();
//        ItemSize itemSize = ItemSize.builder() // productItem, sku, smallMidLargeOneSize, sizeNumber, quantity
//                .productItem(productItem1)
//                .sku(sku)
//                .smallMidLargeOneSize(smallMidLargeOneSize)
//                .sizeNumber(sizeNumber)
//                .quantity(quantity)
//                .build();
//        itemSizeRepository.save(itemSize);
//    }

    public List<ProductResponse> getAllProducts() {
        List<ProductItem> products = productItemRepository.findAll();
        return products.stream().map(this::mapToProductResponse).toList();
    }

    public ProductResponse mapToProductResponse(ProductItem productItem) {
        return ProductResponse.builder()
                .sku(productItem.getSku())
                .price(productItem.getPrice())
                .smallMidLargeOneSize(productItem.getSmallMidLargeOneSize())
                .sizeNumber(productItem.getSizeNumber())
                .quantity(productItem.getQuantity())
                .build();
    }
}
