package com.example.product_category_api.repository;

import com.example.product_category_api.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Page<Product> findByIsEnabledTrue(Pageable pageable);
    Optional<Product> findByUuid(UUID uuid);



}