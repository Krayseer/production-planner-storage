package ru.anykeyers.productionplannerstorage.domain.session.order;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import ru.anykeyers.productionplannerstorage.domain.ProductionType;
import ru.anykeyers.productionplannerstorage.domain.product.Product;
import ru.anykeyers.productionplannerstorage.domain.session.ProductionSession;

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
@ToString(exclude = { "product", "session" })
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
     * Фактическое количество
     */
    private Integer quantityFact;
    /**
     * Крайний срок выполнения
     */
    private LocalDate deadlineDate;
    /**
     * Источник
     */
    private String source;
    /**
     * Статус
     */
    private String status;
    /**
     * Дата создания плана
     */
    @CreationTimestamp
    private LocalDateTime createdAt;

    private Product product;
    private ProductionSession session;
}
