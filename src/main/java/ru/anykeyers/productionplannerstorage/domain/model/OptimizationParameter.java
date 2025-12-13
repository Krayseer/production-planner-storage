package ru.anykeyers.productionplannerstorage.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <b>Параметр оптимизации</b>
 * <p/>
 * Хранение коэффициентов математической модели
 */
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "OPTIMIZATION_PARAMETERS"
)
public class OptimizationParameter {
    /**
     * Стандартное количество максимума бригад в день
     */
    private static final int DEFAULT_MAX_TEAMS_PER_DAY = 4;
    /**
     * Стандартное состояние активности параметра
     */
    private static final Boolean DEFAULT_ACTIVE = Boolean.TRUE;

    /**
     * Уникальный идентификатор параметра
     */
    @Id
    @Column(
            name = "ID"
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    /**
     * Коэффициент "а" в целевой функции
     */
    @Column(
            name = "ALPHA_COEFFICIENT",
            nullable = false,
            precision = 5,
            scale = 3
    )
    private BigDecimal alphaCoefficient;

    /**
     * Максимум бригад в день
     */
    @Column(
            name = "MAX_TEAMS_PER_DAY",
            nullable = false
    )
    @Builder.Default
    private Integer maxTeamsPerDay = DEFAULT_MAX_TEAMS_PER_DAY;

    /**
     * Максимум часов в смену
     */
    @Column(
            name = "MAX_HOURS_PER_SHIFT",
            nullable = false,
            precision = 4,
            scale = 1
    )
    private BigDecimal maxHoursPerShift;

    /**
     * Допустимая разница загрузки "е" для слесарного участка
     */
    @Column(
            name = "LOAD_BALANCE_TOLERANCE",
            nullable = false,
            precision = 4,
            scale = 2
    )
    private BigDecimal loadBalanceTolerance;

    /**
     * Дата создания
     */
    @Column(
            name = "CREATED_AT"
    )
    @CreationTimestamp
    private LocalDateTime createdAt;

    /**
     * Активен ли параметр
     */
    @Column(
            name = "IS_ACTIVE"
    )
    @Builder.Default
    private Boolean active = DEFAULT_ACTIVE;
}
