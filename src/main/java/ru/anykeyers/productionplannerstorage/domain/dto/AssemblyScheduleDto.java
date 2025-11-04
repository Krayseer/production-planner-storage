package ru.anykeyers.productionplannerstorage.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record AssemblyScheduleDto(
        Long id,
        TeamDto team,
        LocalDate date,
        ProductDto product,
        BigDecimal assemblyHours,
        Integer plannedQuantity,
        BigDecimal loadBalanceDeviation,
        LocalDateTime createdAt
) {}
