package ru.anykeyers.productionplannerstorage.controller.dto;

import ru.anykeyers.productionplannerstorage.domain.enums.ProductionType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ProductionPlanDto(
         Long id,
         ProductDto product,
         ProductionType productionType,
         Integer quantity,
         Integer periodMonths,
         Integer priority,
         LocalDate deadlineDate,
         LocalDateTime createdAt
) {}
