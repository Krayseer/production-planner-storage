package ru.anykeyers.productionplannerstorage.domain.optimization.optimizer;


import ru.anykeyers.productionplannerstorage.domain.ProductionType;

import java.math.BigDecimal;
import java.time.LocalDate;

public record OptimizerResult(Long teamId,
                              Integer dayIndex,
                              LocalDate workDate,
                              Long productId,
                              ProductionType productionType,
                              BigDecimal plannedHours,
                              BigDecimal plannedQuantity
) {}
