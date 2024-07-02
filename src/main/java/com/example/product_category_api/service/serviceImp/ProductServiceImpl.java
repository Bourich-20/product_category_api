package com.example.product_category_api.service.serviceImp;


import com.example.product_category_api.entity.Product;
import com.example.product_category_api.repository.ProductRepository;
import com.example.product_category_api.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product getProductByUuid(UUID uuid) {
        return productRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with UUID: " + uuid));
    }

    @Override
    public Product createProduct(Product product) {
        product.setUuid(UUID.randomUUID());
        return productRepository.save(product);
    }

    @Override
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
        product.setEnabled(productDetails.isEnabled());

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(UUID uuid) {
        Product product = getProductByUuid(uuid);
        productRepository.delete(product);
    }

    @Override
    public Page<Product> getProducts(PageRequest pageRequest) {
        return productRepository.findAll(pageRequest);
    }

    @Override
    public Page<Product> getEnabledProducts(Pageable pageable) {
        return productRepository.findByIsEnabledTrue(pageable);
    }
}
