package ru.anykeyers.productionplannerstorage.domain.dto;

import ru.anykeyers.productionplannerstorage.domain.enums.ProductionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TeamProductivityDto(
        Long id,
        TeamDto team,
        ProductDto product,
        ProductionType productionType,
        Integer qualification,
        BigDecimal productivity,
        Boolean active,
        LocalDateTime createdAt
) {}