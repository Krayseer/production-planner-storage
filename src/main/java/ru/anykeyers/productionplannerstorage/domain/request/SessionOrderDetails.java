package ru.anykeyers.productionplannerstorage.domain.request;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record SessionOrderDetails(
        @NotNull(message = "ID продукта обязателен")
        Long productId,

        @NotNull(message = "Тип производства обязателен")
        @Pattern(
                regexp = "serial|non_serial",
                flags = Pattern.Flag.CASE_INSENSITIVE,
                message = "Тип производства должен быть 'serial' или 'non_serial'"
        )
        String productionType,

        @NotNull(message = "Количество обязательно")
        @Positive(message = "Количество должно быть больше 0")
        Integer quantity,

        @NotNull(message = "Крайний срок обязателен")
        @Future(message = "Крайний срок должен быть в будущем")
        LocalDate deadlineDate,

        @NotNull(message = "Источник обязателен")
        String source
) {}
