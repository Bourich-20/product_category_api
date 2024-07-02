package com.example.product_category_api.mapper;

import com.example.product_category_api.DTO.CategoryDTO;
import com.example.product_category_api.entity.Category;

public interface CategoryMapper {

    CategoryDTO toCategoryDTO(Category category);

    Category toCategory(CategoryDTO categoryDTO);
}
