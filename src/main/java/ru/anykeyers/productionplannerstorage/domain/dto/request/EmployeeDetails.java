package ru.anykeyers.productionplannerstorage.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmployeeDetails(
        @NotBlank(message = "full_name не может быть пустым")
        String fullName,

        @NotNull(message = "team_id не может быть null")
        Long teamId,

        @NotBlank(message = "position не может быть пустым")
        String position,

        @NotNull(message = "qualification не может быть null")
        Integer qualification,

        @NotNull(message = "is_active не может быть null")
        Boolean active
) {}
