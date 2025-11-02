package ru.anykeyers.productionplannerstorage.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OptimizationParameterDto(
        Long id,
        BigDecimal alphaCoefficient,
        Integer maxTeamsPerDay,
        BigDecimal maxHoursPerShift,
        BigDecimal loadBalanceTolerance,
        LocalDateTime createdAt,
        @JsonProperty("is_active") Boolean active
) {}
