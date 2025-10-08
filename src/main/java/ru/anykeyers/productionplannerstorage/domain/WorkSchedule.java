package ru.anykeyers.productionplannerstorage.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <b>Табель рабочего времени</b>
 * <p/>
 * Ежедневный учет отработанного времени и отсутствий
 */
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "WORK_SCHEDULES",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {
                        "EMPLOYEE_ID",
                        "WORK_DATE"
                }
        )
)
public class WorkSchedule {
    /**
     * Уникальный идентификатор записи
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
     * Ссылка на сотрудника
     */
    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "EMPLOYEE_ID",
            nullable = false
    )
    private Employee employee;

    /**
     * Дата работы
     */
    @Column(
            name = "WORK_DATE",
            nullable = false
    )
    private LocalDate workDate;

    /**
     * Плановые часы
     */
    @Column(
            name = "HOURS_PLANNED",
            nullable = false,
            precision = 4,
            scale = 1
    )
    private BigDecimal hoursPlanned;

    /**
     * Фактические часы
     */
    @Column(
            name = "HOURS_ACTUAL",
            precision = 4,
            scale = 1
    )
    private BigDecimal hoursActual;

    /**
     * Тип отсутствия
     */
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "ABSENCE_TYPE_ID"
    )
    private AbsenceType absenceType;

    /**
     * Дата создания
     */
    @Column(
            name = "CREATED_AT"
    )
    @CreationTimestamp
    private LocalDateTime createdAt;
}
