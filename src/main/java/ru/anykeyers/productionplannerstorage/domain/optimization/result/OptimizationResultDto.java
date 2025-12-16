package ru.anykeyers.productionplannerstorage.domain.optimization.result;

import ru.anykeyers.productionplannerstorage.domain.ProductionType;
import ru.anykeyers.productionplannerstorage.domain.optimization.run.OptimizationRunDto;
import ru.anykeyers.productionplannerstorage.domain.product.ProductDto;
import ru.anykeyers.productionplannerstorage.domain.session.ProductionSessionDto;
import ru.anykeyers.productionplannerstorage.domain.team.TeamDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record OptimizationResultDto(Long id,
                                    Integer dayIndex,
                                    LocalDate workDate,
                                    ProductionType productionType,
                                    BigDecimal plannedHours,
                                    BigDecimal plannedQuantity,
                                    LocalDateTime createdAt,
                                    OptimizationRunDto optimizationRun,
                                    ProductionSessionDto productionSession,
                                    TeamDto team,
                                    ProductDto product) {
}
