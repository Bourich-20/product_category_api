package com.example.product_category_api.repository;

import com.example.product_category_api.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    Optional<Category> findByUuid(UUID uuid);

}