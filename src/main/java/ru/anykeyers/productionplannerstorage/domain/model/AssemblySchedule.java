package ru.anykeyers.productionplannerstorage.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <b>График слесарного участка</b>
 * <p/>
 * Равномерное распределение между двумя слесарными бригадами (|Z1d - Z2d| <= e)
 */
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "ASSEMBLY_SCHEDULE"
)
public class AssemblySchedule {
    /**
     * Уникальный идентификатор графика
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
     * Ссылка на слесарную бригаду
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
     * Дата работы
     */
    @Column(
            name = "DATE",
            nullable = false
    )
    private LocalDate date;

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
     *  Часы на слесарном участке
     */
    @Column(
            name = "ASSEMBLY_HOURS",
            nullable = false,
            precision = 6,
            scale = 2
    )
    private BigDecimal assemblyHours;

    /**
     * Плановое количество
     */
    @Column(
            name = "PLANNED_QUANTITY",
            nullable = false
    )
    private Integer plannedQuantity;

    /**
     * Отклонение загрузки |Z1d - Z2d|
     */
    @Column(
            name = "LOAD_BALANCE_DEVIATION",
            nullable = false,
            precision = 6,
            scale = 2
    )
    private BigDecimal loadBalanceDeviation;

    /**
     * Дата создания
     */
    @Column(
            name = "CREATED_AT"
    )
    @CreationTimestamp
    private LocalDateTime createdAt;
}
