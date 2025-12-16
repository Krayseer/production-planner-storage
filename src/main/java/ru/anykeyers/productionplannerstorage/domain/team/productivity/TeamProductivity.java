package ru.anykeyers.productionplannerstorage.domain.team.productivity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import ru.anykeyers.productionplannerstorage.domain.ProductionType;
import ru.anykeyers.productionplannerstorage.domain.product.Product;
import ru.anykeyers.productionplannerstorage.domain.team.Team;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <b>Производительность бригад</b>
 * <p/>
 * Матрица "бригада x изделие x тип производства" (Qij)
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamProductivity {
    /**
     * Стандартный флаг активности записи
     */
    private static final Boolean DEFAULT_ACTIVE = Boolean.TRUE;

    /**
     * Уникальный идентификатор производительности
     */
    private Long id;
    /**
     * Тип производства
     */
    private ProductionType productionType;
    /**
     * Квалификация
     * <ul>
     *     <li>0 - нет навыков</li>
     *     <li>1 - серийное</li>
     *     <li>2 - несерийное</li>
     * </ul>
     */
    private Integer qualification;
    /**
     * Производительность (шт/час)
     */
    private BigDecimal productivity;
    /**
     * Флаг активности записи
     */
    @Builder.Default
    private Boolean active = DEFAULT_ACTIVE;
    /**
     * Дата создания
     */
    @CreationTimestamp
    private LocalDateTime createdAt;

    private Team team;
    private Product product;
}
