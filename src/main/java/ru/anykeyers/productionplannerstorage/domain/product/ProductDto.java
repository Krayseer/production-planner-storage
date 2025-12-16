package ru.anykeyers.productionplannerstorage.domain.product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductDto(
        Long id,
        String name,
        BigDecimal assemblyProductivity,
        boolean active,
        LocalDateTime createdAt
) {}
