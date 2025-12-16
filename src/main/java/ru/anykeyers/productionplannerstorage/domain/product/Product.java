package ru.anykeyers.productionplannerstorage.domain.product;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import ru.anykeyers.productionplannerstorage.domain.optimization.result.OptimizationResult;
import ru.anykeyers.productionplannerstorage.domain.session.order.SessionOrder;
import ru.anykeyers.productionplannerstorage.domain.team.productivity.TeamProductivity;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <b>Изделие</b>
 * <p/>
 * Справочник всей производимой продукции
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    /**
     * Стандартный флаг активности изделия
     */
    private static final Boolean DEFAULT_ACTIVE = Boolean.TRUE;

    /**
     * Уникальный идентификатор изделия
     */
    private Long id;
    /**
     * Название изделия
     */
    private String name;
    /**
     * Флаг активности изделия
     */
    @Builder.Default
    private Boolean active = DEFAULT_ACTIVE;
    /**
     * Дата создания записи
     */
    @CreationTimestamp
    private LocalDateTime createdAt;

    private List<SessionOrder> sessionOrders;
    private List<TeamProductivity> teamProductivityList;
    private List<OptimizationResult> optimizationResults;
}
