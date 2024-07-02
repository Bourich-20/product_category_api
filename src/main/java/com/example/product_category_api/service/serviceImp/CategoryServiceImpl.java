package com.example.product_category_api.service.serviceImp;

import com.example.product_category_api.entity.Category;
import com.example.product_category_api.repository.CategoryRepository;
import com.example.product_category_api.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category getCategoryByUuid(UUID uuid) {
        return categoryRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with UUID: " + uuid));
    }

    @Override
    public Category createCategory(Category category) {
        category.setUuid(UUID.randomUUID());
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(UUID uuid, Category categoryDetails) {
        Category category = getCategoryByUuid(uuid);
        if (categoryDetails.getName() != null) {
            category.setName(categoryDetails.getName());
        }
        if (categoryDetails.getDescription() != null) {
            category.setDescription(categoryDetails.getDescription());
        }
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(UUID uuid) {
        Category category = getCategoryByUuid(uuid);
        if (!category.getProducts().isEmpty()) {
            throw new RuntimeException("Cannot delete category with associated products");
        }
        categoryRepository.delete(category);
    }

    @Override
    public Page<Category> getCategories(PageRequest pageRequest) {
        return categoryRepository.findAll(pageRequest);
    }
}
