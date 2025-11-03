package ru.anykeyers.productionplannerstorage.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * <b>Разбивка задач</b>
 * <p/>
 * Отслеживание остатков выполнения задач (Ri в целевой функции)
 */
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "TASK_BREAKDOWN"
)
public class TaskBreakdown {
    /**
     * Стандартный статус задачи
     */
    private static final String DEFAULT_STATUS = "active";

    /**
     * Уникальный идентификатор разбивки
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
     * Ссылка на план
     */
    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "PRODUCTION_PLAN_ID",
            nullable = false
    )
    private ProductionPlan productionPlan;

    /**
     * Общее количество к производству
     */
    @Column(
            name = "TOTAL_QUANTITY",
            nullable = false
    )
    private Integer totalQuantity;

    /**
     * Остаток выполнения (Ri)
     */
    @Column(
            name = "REMAINING_QUANTITY",
            nullable = false
    )
    private Integer remainingQuantity;

    /**
     * Текущий приоритет выполнения
     */
    @Column(
            name = "CURRENT_PRIORITY",
            nullable = false
    )
    private Integer currentPriority;

    /**
     * Статус задачи (active/completed/cancelled)
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

    /**
     * Дата обновления
     */
    @Column(
            name = "UPDATED_AT"
    )
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
