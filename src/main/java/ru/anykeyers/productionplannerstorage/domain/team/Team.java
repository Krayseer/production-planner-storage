package ru.anykeyers.productionplannerstorage.domain.team;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import ru.anykeyers.productionplannerstorage.domain.employee.Employee;
import ru.anykeyers.productionplannerstorage.domain.optimization.OptimizationResult;
import ru.anykeyers.productionplannerstorage.domain.team.productivity.TeamProductivity;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <b>Бригада</b>
 * <p/>
 * Производственные бригады с ресурсами
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    /**
     * Стандартный флаг активности бригады
     */
    private static final Boolean DEFAULT_ACTIVE = Boolean.TRUE;

    /**
     * Уникальный идентификатор бригады
     */
    private Long id;
    /**
     * Название бригады
     */
    private String name;
    /**
     * Тип бригады
     */
    private TeamType teamType;
    /**
     * Количество человек в бригаде
     */
    private Integer employeeCount;
    /**
     * Лимит человеко-часов в месяц
     */
    private Integer monthlyHours;
    /**
     * Максимум часов в смену
     */
    private Integer maxDailyHours;
    /**
     * Флаг активности бригады
     */
    @Builder.Default
    private Boolean active = DEFAULT_ACTIVE;
    /**
     * Дата создания
     */
    @CreationTimestamp
    private LocalDateTime createdAt;

    private List<Employee> employees;
    private List<TeamProductivity> teamProductivityList;
    private List<OptimizationResult> optimizationResults;
}
