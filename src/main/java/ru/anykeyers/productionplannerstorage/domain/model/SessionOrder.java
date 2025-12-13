package ru.anykeyers.productionplannerstorage.domain.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <b>Заказы в сессии</b>
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionOrder {
    /**
     * Уникальный идентификатор плана
     */
    private Long id;
    /**
     * Тип производства
     */
    private ProductionType productionType;
    /**
     * Количество к производству
     */
    private Integer quantity;
    /**
     * Крайний срок выполнения
     */
    private LocalDate deadlineDate;
    /**
     * Источник
     */
    private String source;
    /**
     * Дата создания плана
     */
    @CreationTimestamp
    private LocalDateTime createdAt;

    private Product product;
    private ProductionSession session;
}
