package ru.anykeyers.productionplannerstorage.controller.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record TeamProductivityDetails(
        @NotNull(message = "ID бригады не может быть null")
        Long teamId,

        @NotNull(message = "ID продукта не может быть null")
        Long productId,

        @NotNull(message = "Тип производства обязателен")
        @Pattern(
                regexp = "serial|non_serial",
                flags = Pattern.Flag.CASE_INSENSITIVE,
                message = "Тип производства должен быть 'serial' или 'non_serial'"
        )
        String productionType,

        @NotNull(message = "квалификация не может быть null")
        @Min(value = 0, message = "квалификация должна быть 0, 1 или 2")
        @Max(value = 2, message = "квалификация должна быть 0, 1 или 2")
        Integer qualification,

        @NotNull(message = "производительность не может быть null")
        @DecimalMin(value = "0.0", message = "производительность должна быть >= 0")
        @Digits(integer = 2, fraction = 3, message = "производительность должна иметь формат decimal(5,3)")
        BigDecimal productivity
) {}
