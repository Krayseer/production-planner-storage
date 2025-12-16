package ru.anykeyers.productionplannerstorage.domain.team.productivity;

import ru.anykeyers.productionplannerstorage.domain.ProductionType;
import ru.anykeyers.productionplannerstorage.domain.product.ProductDto;
import ru.anykeyers.productionplannerstorage.domain.team.TeamDto;

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