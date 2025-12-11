package ru.anykeyers.productionplannerstorage.domain.dto;

import ru.anykeyers.productionplannerstorage.domain.enums.ProductionType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record SessionOrderDto(
        Long id,
        ProductDto product,
        Integer quantity,
        ProductionType productionType,
        LocalDate deadlineDate,
        String source,
        LocalDateTime createdAt
) {}
