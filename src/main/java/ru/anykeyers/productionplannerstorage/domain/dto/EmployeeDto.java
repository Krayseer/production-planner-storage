package ru.anykeyers.productionplannerstorage.domain.dto;

import java.time.LocalDateTime;

public record EmployeeDto(
        Long id,
        String fullName,
        TeamDto team,
        String position,
        Integer qualification,
        Boolean active,
        LocalDateTime createdAt
) {}
