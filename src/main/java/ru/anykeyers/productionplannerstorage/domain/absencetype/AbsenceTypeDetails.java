package ru.anykeyers.productionplannerstorage.domain.absencetype;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record AbsenceTypeDetails(
        @NotBlank(message = "code не может быть пустым")
        @Size(min = 1, max = 10, message = "code должно содержать от 1 до 10 символов")
        String code,

        @NotBlank(message = "name не может быть пустым")
        String name,

        @NotNull(message = "impact_factor не может быть null")
        @DecimalMin(value = "0.0", message = "impact_factor должно быть не меньше 0.0")
        @DecimalMax(value = "1.0", message = "impact_factor должно быть не больше 1.0")
        BigDecimal impactFactor,

        @NotNull(message = "is_active не может быть null")
        Boolean active
) {}
