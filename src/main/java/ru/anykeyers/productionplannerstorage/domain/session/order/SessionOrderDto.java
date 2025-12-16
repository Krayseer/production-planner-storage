package ru.anykeyers.productionplannerstorage.domain.session.order;

import ru.anykeyers.productionplannerstorage.domain.ProductionType;
import ru.anykeyers.productionplannerstorage.domain.product.ProductDto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record SessionOrderDto(
        Long id,
        ProductDto product,
        Integer quantity,
        Integer quantityFact,
        ProductionType productionType,
        LocalDate deadlineDate,
        String source,
        String status,
        LocalDateTime createdAt
) {}
