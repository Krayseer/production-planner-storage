package ru.anykeyers.productionplannerstorage.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <b>Смена</b>
 * <p/>
 * Управление ограничением "не более 4 бригад в день" (Wd <= 4)
 */
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "SHIFTS",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {
                        "DATE",
                        "SHIFT_NUMBER"
                }
        )
)
public class Shift {
    /**
     * Стандартное значение максимума бригад в смену
     */
    private static final int DEFAULT_MAX_TEAMS = 4;

    /**
     * Уникальный идентификатор смены
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
     * Дата смены
     */
    @Column(
            name = "DATE",
            nullable = false
    )
    private LocalDate date;

    /**
     * Номер смены: 1 или 2
     */
    @Column(
            name = "SHIFT_NUMBER",
            nullable = false
    )
    private Integer shiftNumber;

    /**
     * Максимум бригад в смену
     */
    @Column(
            name = "MAX_TEAMS",
            nullable = false
    )
    @Builder.Default
    private Integer maxTeams = DEFAULT_MAX_TEAMS;

    /**
     * Доступные часы в смену
     */
    @Column(
            name = "AVAILABLE_HOURS",
            nullable = false,
            precision = 4,
            scale = 1
    )
    private BigDecimal availableHours;

    /**
     * Дата создания
     */
    @Column(
            name = "CREATED_AT"
    )
    @CreationTimestamp
    private LocalDateTime createdAt;
}
