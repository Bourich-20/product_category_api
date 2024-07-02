package com.example.product_category_api.controller;

import com.example.product_category_api.DTO.CategoryDTO;
import com.example.product_category_api.entity.Category;
import com.example.product_category_api.mapper.CategoryMapper;
import com.example.product_category_api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @GetMapping("/getbyUuid/{uuid}")
    public ResponseEntity<CategoryDTO> getCategoryByUuid(@PathVariable UUID uuid) {
        Category category = categoryService.getCategoryByUuid(uuid);
        CategoryDTO categoryDTO = categoryMapper.toCategoryDTO(category);
        return ResponseEntity.ok(categoryDTO);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Category> categories = categoryService.getCategories(PageRequest.of(page, size));
        List<CategoryDTO> categoryDTOs = categories.getContent().stream()
                .map(categoryMapper::toCategoryDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(categoryDTOs);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        Category category = categoryMapper.toCategory(categoryDTO);
        Category createdCategory = categoryService.createCategory(category);
        CategoryDTO createdCategoryDTO = categoryMapper.toCategoryDTO(createdCategory);
        return new ResponseEntity<>(createdCategoryDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update/{uuid}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable UUID uuid, @RequestBody CategoryDTO categoryDTO) {
        Category category = categoryMapper.toCategory(categoryDTO);
        Category updatedCategory = categoryService.updateCategory(uuid, category);
        CategoryDTO updatedCategoryDTO = categoryMapper.toCategoryDTO(updatedCategory);
        return ResponseEntity.ok(updatedCategoryDTO);
    }

    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID uuid) {
        categoryService.deleteCategory(uuid);
        return ResponseEntity.noContent().build();
    }
}
