package ru.anykeyers.productionplannerstorage.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

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
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "OPTIMIZATION_RESULTS"
)
public class OptimizationResult {
    /**
     * Стандартный статус
     */
    private static final String DEFAULT_STATUS = "planned";

    /**
     * Уникальный идентификатор результата
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
     * Ссылка на бригаду
     */
    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "TEAM_ID",
            nullable = false
    )
    private Team team;

    /**
     * Ссылка на изделие
     */
    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "PRODUCT_ID",
            nullable = false
    )
    private Product product;

    /**
     * Тип производства
     */
    @Enumerated(
            value = EnumType.STRING
    )
    @Column(
            name = "PRODUCTION_TYPE",
            nullable = false,
            length = 20
    )
    private ProductionType productionType;

    /**
     * Дата смены
     */
    @Column(
            name = "SHIFT_DATE",
            nullable = false
    )
    private LocalDate shiftDate;

    /**
     * Номер смены
     */
    @Column(
            name = "SHIFT_NUMBER",
            nullable = false
    )
    private Integer shiftNumber;

    /**
     * Назначенные часы работы
     */
    @Column(
            name = "ASSIGNED_HOURS",
            nullable = false,
            precision = 6,
            scale = 2
    )
    private BigDecimal assignedHours;

    /**
     * Доля выполнения задачи xij е [0,1]
     */
    @Column(
            name = "COMPLETION_PERCENTAGE",
            nullable = false,
            precision = 5,
            scale = 4
    )
    private BigDecimal completionPercentage;

    /**
     * Остаток задачи Ri после выполнения
     */
    @Column(
            name = "REMAINING_QUANTITY",
            nullable = false
    )
    private Integer remainingQuantity;

    /**
     * Статус (planned/in_progress/completed)
     */
    @Column(
            name = "STATUS",
            length = 20
    )
    @Builder.Default
    private String status = DEFAULT_STATUS;

    /**
     * Дата создания
     */
    @Column(
            name = "CREATED_AT"
    )
    @CreationTimestamp
    private LocalDateTime createdAt;
}
