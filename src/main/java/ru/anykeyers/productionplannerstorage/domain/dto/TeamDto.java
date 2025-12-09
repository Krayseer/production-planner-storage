package ru.anykeyers.productionplannerstorage.domain.dto;

import ru.anykeyers.productionplannerstorage.domain.enums.TeamType;

import java.time.LocalDateTime;

public record TeamDto(
        Long id,
        String name,
        TeamType teamType,
        Integer employeeCount,
        Integer monthlyHours,
        Integer maxDailyHours,
        Boolean active,
        LocalDateTime createdAt
) {}
