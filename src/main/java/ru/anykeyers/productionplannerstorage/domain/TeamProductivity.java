package ru.anykeyers.productionplannerstorage.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import ru.anykeyers.productionplannerstorage.domain.enums.ProductionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <b>Производительность бригад</b>
 * <p/>
 * Матрица "бригада x изделие x тип производства" (Qij)
 */
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "TEAM_PRODUCTIVITY",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {
                        "TEAM_ID",
                        "PRODUCT_ID",
                        "PRODUCTION_TYPE"
                }
        )
)
public class TeamProductivity {
    /**
     * Стандартный флаг активности записи
     */
    private static final Boolean DEFAULT_ACTIVE = Boolean.TRUE;

    /**
     * Уникальный идентификатор производительности
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
     * Квалификация
     * <ul>
     *     <li>0 - нет навыков</li>
     *     <li>1 - серийное</li>
     *     <li>2 - несерийное</li>
     * </ul>
     */
    @Column(
            name = "QUALIFICATION",
            nullable = false
    )
    private Integer qualification;

    /**
     * Производительность (шт/час)
     */
    @Column(
            name = "PRODUCTIVITY",
            nullable = false,
            precision = 5,
            scale = 3
    )
    private BigDecimal productivity;

    /**
     * Флаг активности записи
     */
    @Column(
            name = "IS_ACTIVE"
    )
    @Builder.Default
    private Boolean active = DEFAULT_ACTIVE;

    /**
     * Дата создания
     */
    @Column(
            name = "CREATED_AT"
    )
    @CreationTimestamp
    private LocalDateTime createdAt;
}
