package ru.anykeyers.productionplannerstorage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anykeyers.productionplannerstorage.domain.WorkSchedule;

import java.time.LocalDate;

/**
 * DAO табелей рабочего времени
 */
public interface WorkScheduleRepository extends JpaRepository<WorkSchedule, Long> {

    /**
     * Существует ли табель рабочего времени с указанными идентификатором сотрудника и датой работы
     *
     * @param employeeId    идентификатор сотрудника
     * @param workDate      дата работы
     */
    boolean existsByEmployeeIdAndWorkDate(Long employeeId, LocalDate workDate);

}
