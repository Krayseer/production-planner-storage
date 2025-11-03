package ru.anykeyers.productionplannerstorage.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.anykeyers.productionplannerstorage.domain.enums.ProductionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TeamProductivityDto(
        Long id,
        TeamDto team,
        ProductDto product,
        ProductionType productionType,
        Integer qualification,
        BigDecimal productivity,
        @JsonProperty("is_active") Boolean active,
        LocalDateTime createdAt
) {}