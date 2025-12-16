package ru.anykeyers.productionplannerstorage.domain.employee;

import ru.anykeyers.productionplannerstorage.domain.team.TeamDto;

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
