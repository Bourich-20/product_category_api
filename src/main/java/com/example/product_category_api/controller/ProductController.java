package com.example.product_category_api.controller;


import com.example.product_category_api.entity.Product;
import com.example.product_category_api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/getbyUuid/{uuid}")
    public ResponseEntity<Product> getProductByUuid(@PathVariable UUID uuid) {
        Product product = productService.getProductByUuid(uuid);
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<Page<Product>> getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Product> products = productService.getProducts(PageRequest.of(page, size));
        return ResponseEntity.ok(products);
    }

    @GetMapping("/enabled")
    public ResponseEntity<Page<Product>> getEnabledProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Product> products = productService.getEnabledProducts(PageRequest.of(page, size));
        return ResponseEntity.ok(products);
    }


    @PostMapping("/create/")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PatchMapping("/update/{uuid}")
    public ResponseEntity<Product> updateProduct(@PathVariable UUID uuid, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(uuid, product);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID uuid) {
        productService.deleteProduct(uuid);
        return ResponseEntity.noContent().build();
    }
}