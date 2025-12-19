package ru.anykeyers.productionplannerstorage.domain.optimization.run;

import lombok.*;
import ru.anykeyers.productionplannerstorage.domain.session.ProductionSession;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * <b>Параметр оптимизации</b>
 * <p/>
 * Хранение коэффициентов математической модели
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "productionSession")
public class OptimizationRun {
    private Long id;
    private LocalDate runTimestamp;
    private String modelVersion;
    private BigDecimal tardyDefaultK;
    private BigDecimal underK;
    private BigDecimal overK;
    private BigDecimal alpha;
    private BigDecimal beta;
    private BigDecimal deltaBuffer;
    private String comment;
    private Double objectiveValue;

    private ProductionSession productionSession;
}
