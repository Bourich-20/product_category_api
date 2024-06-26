package com.example.product_category_api.service;


import com.example.product_category_api.repository.ProductRepository;
import com.example.product_category_api.entity.Product;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product getProductByUuid(UUID uuid) {
        return productRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with UUID: " + uuid));
    }

    public Product createProduct(Product product) {
        product.setUuid(UUID.randomUUID());
        return productRepository.save(product);
    }

    public Product updateProduct(UUID uuid, Product productDetails) {
        Product product = getProductByUuid(uuid);

        if (productDetails.getTitle() != null) {
            product.setTitle(productDetails.getTitle());
        }
        if (productDetails.getDescription() != null) {
            product.setDescription(productDetails.getDescription());
        }
        if (productDetails.getPrice() != null) {
            product.setPrice(productDetails.getPrice());
        }
        if (productDetails.getCategory() != null) {
            product.setCategory(productDetails.getCategory());
        }
        if (productDetails.isEnabled()== true || productDetails.isEnabled()== true) {
            product.setEnabled(productDetails.isEnabled());
        }

        return productRepository.save(product);
    }


    public void deleteProduct(UUID uuid) {
        Product product = getProductByUuid(uuid);
        productRepository.delete(product);
    }

    public Page<Product> getProducts(PageRequest pageRequest) {
        return productRepository.findAll(pageRequest);
    }

    public Page<Product> getEnabledProducts(Pageable pageable) {
        return productRepository.findByIsEnabledTrue(pageable);
    }
}