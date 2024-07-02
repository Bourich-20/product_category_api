package com.example.product_category_api.service;

import com.example.product_category_api.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ProductService {
    Product getProductByUuid(UUID uuid);
    Product createProduct(Product product);
    Product updateProduct(UUID uuid, Product productDetails);
    void deleteProduct(UUID uuid);
    Page<Product> getProducts(PageRequest pageRequest);
    Page<Product> getEnabledProducts(Pageable pageable);
}
