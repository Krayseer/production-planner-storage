package ru.anykeyers.productionplannerstorage.domain.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record ProductionSessionDto(
        Long id,
        String name,
        LocalDate startDate,
        LocalDate endDate,
        String status,
        LocalDateTime createdAt,
        List<SessionOrderDto> sessionOrders
) {}
