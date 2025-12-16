package ru.anykeyers.productionplannerstorage.domain.optimization.run;

import java.math.BigDecimal;
import java.time.LocalDate;

public record OptimizationRunDetails(
        LocalDate runTimestamp,
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

