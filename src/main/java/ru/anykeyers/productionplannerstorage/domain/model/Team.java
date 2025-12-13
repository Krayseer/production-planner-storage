package ru.anykeyers.productionplannerstorage.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <b>Бригада</b>
 * <p/>
 * Производственные бригады с ресурсами
 */
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "TEAMS"
)
public class Team {
    /**
     * Стандартный флаг активности бригады
     */
    private static final Boolean DEFAULT_ACTIVE = Boolean.TRUE;

    /**
     * Уникальный идентификатор бригады
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
     * Название бригады
     */
    @Column(
            name = "NAME",
            nullable = false, 
            length = 100
    )
    private String name;

    /**
     * Тип бригады
     */
    @Enumerated(
            value = EnumType.STRING
    )
    @Column(
            name = "TEAM_TYPE",
            nullable = false,
            length = 20
    )
    private TeamType teamType;

    /**
     * Количество человек в бригаде
     */
    @Column(
            name = "EMPLOYEE_COUNT",
            nullable = false
    )
    private Integer employeeCount;

    /**
     * Лимит человеко-часов в месяц
     */
    @Column(
            name = "MONTHLY_HOURS",
            nullable = false
    )
    private Integer monthlyHours;

    /**
     * Максимум часов в смену
     */
    @Column(
            name = "MAX_DAILY_HOURS",
            nullable = false
    )
    private Integer maxDailyHours;

    /**
     * Флаг активности бригады
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

    @OneToMany(
            mappedBy = "team",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<TeamProductivity> teamProductivityList;

    @OneToMany(
            mappedBy = "team",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<OptimizationResult> optimizationResults;

    @OneToMany(
            mappedBy = "team",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<AssemblySchedule> assemblySchedules;

    @OneToMany(
            mappedBy = "team",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<Employee> employees;
}
