package com.example.product_category_api.service;


import com.example.product_category_api.repository.CategoryRepository;
import com.example.product_category_api.entity.Category;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category getCategoryByUuid(UUID uuid) {
        return categoryRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with UUID: " + uuid));
    }
    public Category createCategory(Category category) {
        category.setUuid(UUID.randomUUID());
        return categoryRepository.save(category);
    }

    public Category updateCategory(UUID uuid, Category categoryDetails) {
        Category category = getCategoryByUuid(uuid);
        if(categoryDetails.getName() != null){
            category.setName(categoryDetails.getName());

        }
        if(categoryDetails.getDescription() != null){
            category.setDescription(categoryDetails.getDescription());

        }

        return categoryRepository.save(category);
    }

    public void deleteCategory(UUID uuid) {
        Category category = getCategoryByUuid(uuid);
        if (!category.getProducts().isEmpty()) {
            throw new RuntimeException("Cannot delete category with associated products");
        }
        categoryRepository.delete(category);
    }

    public Page<Category> getCategories(PageRequest pageRequest) {
        return categoryRepository.findAll(pageRequest);
    }
}