package ru.anykeyers.productionplannerstorage.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductDto(
        Long id,
        String name,
        BigDecimal assemblyProductivity,
        @JsonProperty("is_active") boolean active,
        LocalDateTime createdAt
) {}
