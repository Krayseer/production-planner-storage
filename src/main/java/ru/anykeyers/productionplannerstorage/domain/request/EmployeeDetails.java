package ru.anykeyers.productionplannerstorage.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
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

        @JsonProperty("is_active")
        @NotNull(message = "is_active не может быть null")
        Boolean active
) {}
