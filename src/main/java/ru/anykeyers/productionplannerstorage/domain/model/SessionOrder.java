package ru.anykeyers.productionplannerstorage.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <b>Заказы в сессии</b>
 */
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "SESSION_ORDERS"
)
public class SessionOrder {

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
     * Ссылка на сессию
     */
    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "SESSION_ID",
            nullable = false
    )
    private ProductionSession session;

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
     * Крайний срок выполнения
     */
    @Column(
            name = "DEADLINE_DATE"
    )
    private LocalDate deadlineDate;

    /**
     * Источник
     */
    @Column(
            name = "SOURCE"
    )
    private String source;

    /**
     * Дата создания плана
     */
    @Column(
            name = "CREATED_AT"
    )
    @CreationTimestamp
    private LocalDateTime createdAt;
}
