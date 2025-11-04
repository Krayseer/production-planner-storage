package ru.anykeyers.productionplannerstorage.domain.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AssemblyScheduleDetails(
        @NotNull(message = "team_id не может быть null")
        Long teamId,

        @NotNull(message = "date не может быть null")
        LocalDate date,

        @NotNull(message = "product_id не может быть null")
        Long productId,

        @NotNull(message = "assembly_hours не может быть null")
        BigDecimal assemblyHours,

        @NotNull(message = "planned_quantity не может быть null")
        Integer plannedQuantity
) {}
