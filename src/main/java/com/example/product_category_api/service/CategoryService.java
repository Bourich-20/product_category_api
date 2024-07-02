package com.example.product_category_api.service;


import com.example.product_category_api.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface CategoryService {
    Category getCategoryByUuid(UUID uuid);
    Category createCategory(Category category);
    Category updateCategory(UUID uuid, Category categoryDetails);
    void deleteCategory(UUID uuid);
    Page<Category> getCategories(PageRequest pageRequest);
}
