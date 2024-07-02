package com.example.product_category_api.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ProductDTO(
        Long id,
        UUID uuid,
        String title,
        String description,
        BigDecimal price,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        boolean isEnabled,
        Long categoryId
) {
}
