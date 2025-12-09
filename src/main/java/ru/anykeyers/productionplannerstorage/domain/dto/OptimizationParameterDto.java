package ru.anykeyers.productionplannerstorage.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OptimizationParameterDto(
        Long id,
        BigDecimal alphaCoefficient,
        Integer maxTeamsPerDay,
        BigDecimal maxHoursPerShift,
        BigDecimal loadBalanceTolerance,
        LocalDateTime createdAt,
        Boolean active
) {}
