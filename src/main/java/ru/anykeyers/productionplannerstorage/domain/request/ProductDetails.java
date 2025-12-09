package ru.anykeyers.productionplannerstorage.domain.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductDetails(
        @NotBlank(message = "Название обязательно для заполнения")
        @Size(min = 3, max = 255, message = "Длина имени должна быть от 3 до 255 символов")
        String name,

        @NotNull(message = "Производительность сборки обязательна для заполнения")
        @DecimalMin(value = "0.01", message = "Производительность должна быть больше 0")
        @Digits(integer = 3, fraction = 2, message = "Формат должен быть decimal(5,2)")
        BigDecimal assemblyProductivity,

        boolean active
) {}
