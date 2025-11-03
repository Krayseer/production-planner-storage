package ru.anykeyers.productionplannerstorage.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <b>Сотрудник</b>
 * <p/>
 * Персональный состав бригад для табельного учета
 */
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "EMPLOYEES"
)
public class Employee {
    /**
     * Стандартный флаг активности
     */
    private static final Boolean DEFAULT_ACTIVE = Boolean.TRUE;

    /**
     * Уникальный идентификатор сотрудника
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
     * ФИО сотрудника
     */
    @Column(
            name = "FULL_NAME",
            nullable = false
    )
    private String fullName;

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
     * Должность
     */
    @Column(
            name = "POSITION",
            length = 100
    )
    private String position;

    /**
     * Уровень квалификации
     */
    @Column(
            name = "QUALIFICATION"
    )
    private Integer qualification;

    /**
     * Флаг активности
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
            mappedBy = "employee",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<WorkSchedule> workSchedules;
}
