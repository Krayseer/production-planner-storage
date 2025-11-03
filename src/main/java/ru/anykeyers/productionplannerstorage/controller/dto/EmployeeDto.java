package ru.anykeyers.productionplannerstorage.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record EmployeeDto(
        Long id,
        String fullName,
        TeamDto team,
        String position,
        Integer qualification,
        @JsonProperty("is_active") Boolean active,
        LocalDateTime createdAt
) {}
