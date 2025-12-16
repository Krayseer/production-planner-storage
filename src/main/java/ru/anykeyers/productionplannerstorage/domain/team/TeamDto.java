package ru.anykeyers.productionplannerstorage.domain.team;

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
