package ru.anykeyers.productionplannerstorage.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <b>Тип отсутствия</b>
 * <p/>
 * Справочник причин отсутствия на работе
 */
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "ABSENCE_TYPES"
)
public class AbsenceType {
    /**
     * Стандартный флаг активности
     */
    private static final Boolean DEFAULT_ACTIVE = Boolean.TRUE;
    /**
     * Стандартное влияние на производительность
     */
    private static BigDecimal DEFAULT_IMPACT_FACTOR = BigDecimal.ZERO;

    /**
     * Уникальный идентификатор типа
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
     * Код отсутствия
     * <ul>
     *     <li>'О' - отпуск</li>
     *     <li>'Б' - больничный</li>
     *     <li>'А' - без содержания</li>
     * </ul>
     */
    @Column(
            name = "CODE",
            nullable = false,
            unique = true,
            length = 10
    )
    private String code;

    /**
     * Название типа
     */
    @Column(
            name = "NAME",
            nullable = false,
            length = 100
    )
    private String name;

    /**
     * Влияние на производительность (0.0 - 1.0)
     */
    @Column(
            name = "IMPACT_FACTOR",
            nullable = false,
            precision = 3,
            scale = 2
    )
    private BigDecimal impactFactor = DEFAULT_IMPACT_FACTOR;

    /**
     * Флаг активности
     */
    @Column(
            name = "IS_ACTIVE"
    )
    private Boolean active = DEFAULT_ACTIVE;

    /**
     * Дата создания
     */
    @Column(
            name = "CREATED_AT"
    )
    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(
            mappedBy = "absenceType",
            fetch = FetchType.LAZY
    )
    private List<WorkSchedule> workSchedules;
}
