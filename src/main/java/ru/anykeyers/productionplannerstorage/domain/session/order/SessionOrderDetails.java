package ru.anykeyers.productionplannerstorage.domain.session.order;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record SessionOrderDetails(
        @NotNull(message = "ID продукта обязателен")
        Long productId,

        @NotNull(message = "Тип производства обязателен")
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
