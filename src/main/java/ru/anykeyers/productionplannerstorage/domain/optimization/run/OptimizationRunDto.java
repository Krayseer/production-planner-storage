package ru.anykeyers.productionplannerstorage.domain.optimization.run;

import ru.anykeyers.productionplannerstorage.domain.session.ProductionSessionDto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record OptimizationRunDto(
        Long id,
        LocalDate runTimestamp,
        String modelVersion,
        BigDecimal kTardyDefault,
        BigDecimal kUnder,
        BigDecimal kOver,
        BigDecimal alpha,
        BigDecimal beta,
        BigDecimal deltaBuffer,
        String comment,
        ProductionSessionDto productionSession
) {}
