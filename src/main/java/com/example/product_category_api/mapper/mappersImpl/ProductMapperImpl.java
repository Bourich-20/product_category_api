package com.example.product_category_api.mapper.mappersImpl;

import com.example.product_category_api.DTO.ProductDTO;
import com.example.product_category_api.entity.Product;
import com.example.product_category_api.entity.Category;
import com.example.product_category_api.mapper.ProductMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDTO toProductDTO(Product product) {
        if (product == null) {
            return null;
        }

        return new ProductDTO(
                product.getId(),
                product.getUuid(),
                product.getTitle(),
                product.getDescription(),
                product.getPrice(),
                product.getCreatedAt(),
                product.getUpdatedAt(),
                product.isEnabled(),
                product.getCategory().getId()
        );
    }

    @Override
    public Product toProduct(ProductDTO productDTO) {
        if (productDTO == null) {
            return null;
        }

        Product product = new Product();
        product.setId(productDTO.id());
        product.setUuid(productDTO.uuid());
        product.setTitle(productDTO.title());
        product.setDescription(productDTO.description());

        Category category = new Category();
        category.setId(productDTO.categoryId());
        product.setCategory(category);

        product.setPrice(productDTO.price());
        product.setCreatedAt(productDTO.createdAt());
        product.setUpdatedAt(productDTO.updatedAt());
        product.setEnabled(productDTO.isEnabled());

        return product;
    }
}
