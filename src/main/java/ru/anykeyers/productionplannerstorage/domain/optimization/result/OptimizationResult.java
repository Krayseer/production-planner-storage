package ru.anykeyers.productionplannerstorage.domain.optimization.result;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import ru.anykeyers.productionplannerstorage.domain.ProductionType;
import ru.anykeyers.productionplannerstorage.domain.optimization.run.OptimizationRun;
import ru.anykeyers.productionplannerstorage.domain.product.Product;
import ru.anykeyers.productionplannerstorage.domain.session.ProductionSession;
import ru.anykeyers.productionplannerstorage.domain.team.Team;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <b>Результат оптимизации</b>
 * <p/>
 * Выходные данные математической модели (план работ)
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OptimizationResult {
    private Long id;
    private Integer dayIndex;
    private LocalDate workDate;
    private ProductionType productionType;
    private BigDecimal plannedHours;
    private BigDecimal plannedQuantity;
    @CreationTimestamp
    private LocalDateTime createdAt;

    private OptimizationRun optimizationRun;
    private ProductionSession productionSession;
    private Team team;
    private Product product;
}
