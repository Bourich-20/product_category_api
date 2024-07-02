package com.example.product_category_api.DTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record CategoryDTO(
        Long id,
        UUID uuid,
        String name,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<ProductDTO> products
) {
}
