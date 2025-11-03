package ru.anykeyers.productionplannerstorage.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import ru.anykeyers.productionplannerstorage.domain.enums.ProductionType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <b>План производства</b>
 * <p/>
 * Количественные цели по изделиям на 3 месяца
 */
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "PRODUCTION_PLAN"
)
public class ProductionPlan {
    /**
     * Стандартный период планирования
     */
    private static final int DEFAULT_PERIOD_MONTH = 3;
    /**
     * Стандартный приоритет выполнения
     */
    private static final int DEFAULT_PRIORITY = 1;

    /**
     * Уникальный идентификатор плана
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
     * Количество к производству
     */
    @Column(
            name = "QUANTITY",
            nullable = false
    )
    private Integer quantity;

    /**
     * Период планирования, всегда {@link #DEFAULT_PERIOD_MONTH}
     */
    @Column(
            name = "PERIOD_MONTH"
    )
    @Builder.Default
    private Integer periodMonths = DEFAULT_PERIOD_MONTH;

    /**
     * Приоритет выполнения
     */
    @Column(
            name = "PRIORITY"
    )
    @Builder.Default
    private Integer priority = DEFAULT_PRIORITY;

    /**
     * Крайний срок выполнения
     */
    @Column(
            name = "DEADLINE_DATE"
    )
    private LocalDate deadlineDate;

    /**
     * Дата создания плана
     */
    @Column(
            name = "CREATED_AT"
    )
    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(
            mappedBy = "productionPlan",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<TaskBreakdown> taskBreakdowns;
}
