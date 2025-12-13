package ru.anykeyers.productionplannerstorage.domain.model;

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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductionSession {
    /**
     * Стандартный статус сессии
     */
    private static final String DEFAULT_STATUS = "draft";

    /**
     * Уникальный идентификатор сессии
     */
    private Long id;
    /**
     * Название сессии
     */
    private String name;
    /**
     * Дата начала сессии
     */
    private LocalDate startDate;
    /**
     * Дата окончания сессии
     */
    private LocalDate endDate;
    /**
     * Статус задачи (draft)
     */
    @Builder.Default
    private String status = DEFAULT_STATUS;
    /**
     * Дата создания сессии
     */
    @CreationTimestamp
    private LocalDateTime createdAt;

    private List<SessionOrder> sessionOrders;
}
