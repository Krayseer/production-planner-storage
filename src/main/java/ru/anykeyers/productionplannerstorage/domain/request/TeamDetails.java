package ru.anykeyers.productionplannerstorage.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

public record TeamDetails(
        @NotBlank(message = "название бригады не может быть пустым")
        @Size(max = 100, message = "название бригады не может превышать 100 символов")
        String name,

        @NotBlank(message = "тип бригады не может быть пустым")
        @Pattern(
                regexp = "^(production|assembly)$",
                message = "тип бригады должен быть 'production' или 'assembly'"
        )
        String teamType,

        @NotNull(message = "количество сотрудников не может быть null")
        @Min(value = 1, message = "количество сотрудников должно быть больше 0")
        Integer employeeCount,

        @NotNull(message = "лимит человеко-часов не может быть null")
        @Min(value = 1, message = "лимит человеко-часов должен быть больше 0")
        Integer monthlyHours,

        @NotNull(message = "максимум часов в смену не может быть null")
        @Min(value = 1, message = "максимум часов в смену должен быть не меньше 1")
        @Max(value = 24, message = "максимум часов в смену должен быть не больше 24")
        Integer maxDailyHours,

        @JsonProperty("is_active")
        Boolean active
) {}
