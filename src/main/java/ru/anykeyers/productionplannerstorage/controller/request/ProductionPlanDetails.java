package ru.anykeyers.productionplannerstorage.controller.request;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record ProductionPlanDetails(
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

        @NotNull(message = "Период планирования обязателен")
        @Min(value = 1, message = "Минимальный период — 1 месяц")
        @Max(value = 12, message = "Максимальный период — 12 месяцев")
        Integer periodMonths,

        @NotNull(message = "Приоритет обязателен")
        @Min(value = 1, message = "Минимальный приоритет — 1")
        @Max(value = 10, message = "Максимальный приоритет — 10")
        Integer priority,

        @NotNull(message = "Крайний срок обязателен")
        @Future(message = "Крайний срок должен быть в будущем")
        LocalDate deadlineDate
) {}
