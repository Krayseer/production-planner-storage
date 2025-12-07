package ru.anykeyers.productionplannerstorage.domain.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ProductionSessionDetails(
        @NotBlank(message = "название не может быть пустым")
        String name,
        @NotNull(message = "start_date не может быть null")
        LocalDate startDate
) {}
