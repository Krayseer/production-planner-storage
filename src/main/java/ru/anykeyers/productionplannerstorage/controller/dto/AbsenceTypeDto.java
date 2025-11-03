package ru.anykeyers.productionplannerstorage.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AbsenceTypeDto(
        Long id,
        String code,
        String name,
        BigDecimal impactFactor,
        @JsonProperty("is_active") Boolean active,
        LocalDateTime createdAt
) {}
