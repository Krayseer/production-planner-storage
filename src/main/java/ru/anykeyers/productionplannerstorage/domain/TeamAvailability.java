package ru.anykeyers.productionplannerstorage.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <b>Доступность бригад</b>
 * <p/>
 * График работы 2/2 и доступность бригад
 */
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "TEAM_AVAILABILITY",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {
                        "TEAM_ID",
                        "DATE"
                }
        )
)
public class TeamAvailability {
    /**
     * Стандартное значение доступности бригады в {@link #date дату}
     */
    private static final Boolean DEFAULT_AVAILABLE = Boolean.TRUE;

    /**
     * Уникальный идентификатор доступности
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
     * Дата
     */
    @Column(
            name = "DATE",
            nullable = false
    )
    private LocalDate date;

    /**
     * Доступна ли бригада в эту {@link #date дату}
     */
    @Column(
            name = "IS_AVAILABLE"
    )
    private Boolean available = DEFAULT_AVAILABLE;

    /**
     * Доступные часы работы
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
