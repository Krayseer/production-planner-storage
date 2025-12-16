package ru.anykeyers.productionplannerstorage.domain.optimization;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OptimizationRunDetails(
        LocalDateTime runTimestamp,
        String modelVersion,
        BigDecimal kTardyDefault,
        BigDecimal kUnder,
        BigDecimal kOver,
        BigDecimal alpha,
        BigDecimal beta,
        BigDecimal deltaBuffer,
        String comment,
        Long productionSessionId
) {}

