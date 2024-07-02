package com.example.product_category_api.mapper.mappersImpl;

import com.example.product_category_api.DTO.CategoryDTO;
import com.example.product_category_api.DTO.ProductDTO;
import com.example.product_category_api.entity.Category;
import com.example.product_category_api.entity.Product;
import com.example.product_category_api.mapper.CategoryMapper;
import com.example.product_category_api.mapper.ProductMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapperImpl implements CategoryMapper {

    private final ProductMapper productMapper;

    public CategoryMapperImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public CategoryDTO toCategoryDTO(Category category) {
        if (category == null) {
            return null;
        }

        List<ProductDTO> productDTOs = category.getProducts().stream()
                .map(productMapper::toProductDTO)
                .collect(Collectors.toList());

        return new CategoryDTO(
                category.getId(),
                category.getUuid(),
                category.getName(),
                category.getDescription(),
                category.getCreatedAt(),
                category.getUpdatedAt(),
                productDTOs
        );
    }

    @Override
    public Category toCategory(CategoryDTO categoryDTO) {
        if (categoryDTO == null) {
            return null;
        }

        Category category = new Category();
        category.setId(categoryDTO.id());
        category.setUuid(categoryDTO.uuid());
        category.setName(categoryDTO.name());
        category.setDescription(categoryDTO.description());
        category.setCreatedAt(categoryDTO.createdAt());
        category.setUpdatedAt(categoryDTO.updatedAt());

        List<Product> products = categoryDTO.products().stream()
                .map(productMapper::toProduct)
                .collect(Collectors.toList());

        category.setProducts(products);

        return category;
    }
}
