package ru.anykeyers.productionplannerstorage.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <b>Изделие</b>
 * <p/>
 * Справочник всей производимой продукции
 */
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "PRODUCTS"
)
public class Product {
    /**
     * Стандартный флаг активности изделия
     */
    private static final Boolean DEFAULT_ACTIVE = Boolean.TRUE;

    /**
     * Уникальный идентификатор изделия
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
     * Название изделия
     */
    @Column(
            name = "NAME",
            nullable = false
    )
    private String name;

    /**
     * Производительность на слесарном участке (шт/час)
     */
    @Column(
            name = "ASSEMBLY_PRODUCTIVITY",
            precision = 5,
            scale = 2,
            nullable = false
    )
    private BigDecimal assemblyProductivity;

    /**
     * Флаг активности изделия
     */
    @Column(
            name = "IS_ACTIVE"
    )
    @Builder.Default
    private Boolean active = DEFAULT_ACTIVE;

    /**
     * Дата создания записи
     */
    @Column(
            name = "CREATED_AT"
    )
    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(
            mappedBy = "product",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<SessionOrder> sessionOrders;

    @OneToMany(
            mappedBy = "product",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<TeamProductivity> teamProductivityList;

    @OneToMany(
            mappedBy = "product",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<OptimizationResult> optimizationResults;

    @OneToMany(
            mappedBy = "product",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<AssemblySchedule> assemblySchedules;
}
