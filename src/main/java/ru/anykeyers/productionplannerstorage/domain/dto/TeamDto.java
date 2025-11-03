package ru.anykeyers.productionplannerstorage.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.anykeyers.productionplannerstorage.domain.enums.TeamType;

import java.time.LocalDateTime;

public record TeamDto(
        Long id,
        String name,
        TeamType teamType,
        Integer employeeCount,
        Integer monthlyHours,
        Integer maxDailyHours,
        @JsonProperty("is_active") Boolean active,
        LocalDateTime createdAt
) {}
