package ru.anykeyers.productionplannerstorage.domain.optimization;

import lombok.*;
import ru.anykeyers.productionplannerstorage.domain.session.ProductionSession;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <b>Параметр оптимизации</b>
 * <p/>
 * Хранение коэффициентов математической модели
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OptimizationRun {
    private Long id;
    private LocalDateTime runTimestamp;
    private String modelVersion;
    private BigDecimal kTardyDefault;
    private BigDecimal kUnder;
    private BigDecimal kOver;
    private BigDecimal alpha;
    private BigDecimal beta;
    private BigDecimal deltaBuffer;
    private String comment;
    private ProductionSession productionSession;
}
