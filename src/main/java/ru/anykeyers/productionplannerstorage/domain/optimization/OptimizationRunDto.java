package ru.anykeyers.productionplannerstorage.domain.optimization;

import ru.anykeyers.productionplannerstorage.domain.session.ProductionSessionDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OptimizationRunDto(
        Long id,
        LocalDateTime runTimestamp,
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
