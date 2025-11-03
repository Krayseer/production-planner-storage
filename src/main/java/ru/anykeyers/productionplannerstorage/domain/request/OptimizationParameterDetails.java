package ru.anykeyers.productionplannerstorage.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record OptimizationParameterDetails(
        @NotNull(message = "коэффициент 'α' не может быть null")
        @DecimalMin(value = "0.0", inclusive = false, message = "коэффициент 'α' должен быть больше 0")
        BigDecimal alphaCoefficient,

        @NotNull(message = "максимум бригад в день не может быть null")
        @Min(value = 1, message = "максимум бригад в день должен быть не меньше 1")
        @Max(value = 10, message = "максимум бригад в день должен быть не больше 10")
        Integer maxTeamsPerDay,

        @NotNull(message = "максимум часов в смену не может быть null")
        @DecimalMin(value = "1.0", message = "максимум часов в смену должен быть не меньше 1")
        @DecimalMax(value = "24.0", message = "максимум часов в смену должен быть не больше 24")
        BigDecimal maxHoursPerShift,

        @NotNull(message = "допустимая разница загрузки 'ε' не может быть null")
        @DecimalMin(value = "0.0", message = "допустимая разница загрузки 'ε' должна быть не меньше 0")
        BigDecimal loadBalanceTolerance,

        @JsonProperty("is_active")
        boolean active
) {}

