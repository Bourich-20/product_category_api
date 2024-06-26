package com.example.product_category_api.controller;



import com.example.product_category_api.entity.Category;
import com.example.product_category_api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getbyUuid/{uuid}")
    public ResponseEntity<Category> getCategoryByUuid(@PathVariable UUID uuid) {
        Category category = categoryService.getCategoryByUuid(uuid);
        return ResponseEntity.ok(category);
    }

    @GetMapping
    public ResponseEntity<Page<Category>> getCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Category> categories = categoryService.getCategories(PageRequest.of(page, size));
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category createdCategory = categoryService.createCategory(category);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @PutMapping("/update/{uuid}")
    public ResponseEntity<Category> updateCategory(@PathVariable UUID uuid, @RequestBody Category category) {
        Category updatedCategory = categoryService.updateCategory(uuid, category);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID uuid) {
        categoryService.deleteCategory(uuid);
        return ResponseEntity.noContent().build();
    }
}