package ru.anykeyers.productionplannerstorage.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <b>Производственная сессия</b>
 * <p/>
 * Начальник создает сессию на указанное количество дней. Внутри нее будут заказы
 */
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "PRODUCTION_SESSIONS"
)
public class ProductionSession {
    /**
     * Стандартный статус сессии
     */
    private static final String DEFAULT_STATUS = "draft";

    /**
     * Уникальный идентификатор сессии
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
     * Название сессии
     */
    @Column(
            name = "NAME"
    )
    private String name;

    /**
     * Дата начала сессии
     */
    @Column(
            name = "START_DATE",
            nullable = false
    )
    private LocalDate startDate;

    /**
     * Дата окончания сессии
     */
    @Column(
            name = "END_DATE",
            nullable = false
    )
    private LocalDate endDate;

    /**
     * Статус задачи (draft)
     */
    @Column(
            name = "STATUS",
            length = 20
    )
    @Builder.Default
    private String status = DEFAULT_STATUS;

    /**
     * Дата создания сессии
     */
    @Column(
            name = "CREATED_AT"
    )
    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(
            mappedBy = "session",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<SessionOrder> sessionOrders;
}
