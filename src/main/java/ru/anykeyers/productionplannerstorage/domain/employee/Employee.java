package ru.anykeyers.productionplannerstorage.domain.employee;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import ru.anykeyers.productionplannerstorage.domain.workshedule.WorkSchedule;
import ru.anykeyers.productionplannerstorage.domain.team.Team;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <b>Сотрудник</b>
 * <p/>
 * Персональный состав бригад для табельного учета
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = { "team", "workSchedules" })
public class Employee {
    /**
     * Стандартный флаг активности
     */
    private static final Boolean DEFAULT_ACTIVE = Boolean.TRUE;

    /**
     * Уникальный идентификатор сотрудника
     */
    private Long id;
    /**
     * ФИО сотрудника
     */
    private String fullName;
    /**
     * Должность
     */
    private String position;
    /**
     * Уровень квалификации
     */
    private Integer qualification;
    /**
     * Флаг активности
     */
    @Builder.Default
    private Boolean active = DEFAULT_ACTIVE;
    /**
     * Дата создания
     */
    @CreationTimestamp
    private LocalDateTime createdAt;

    private Team team;
    private List<WorkSchedule> workSchedules;
}
