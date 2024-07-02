package com.example.product_category_api.mapper;

import com.example.product_category_api.DTO.ProductDTO;
import com.example.product_category_api.entity.Product;

public interface ProductMapper {

    ProductDTO toProductDTO(Product product);

    Product toProduct(ProductDTO productDTO);
}
