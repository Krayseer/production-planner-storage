package ru.anykeyers.productionplannerstorage.infrastructure.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.anykeyers.productionplannerstorage.domain.model.WorkSchedule;

import java.time.LocalDate;

/**
 * DAO табелей рабочего времени
 */
@Repository
public interface WorkScheduleRepository extends JpaRepository<WorkSchedule, Long> {

    /**
     * Существует ли табель рабочего времени с указанными идентификатором сотрудника и датой работы
     *
     * @param employeeId    идентификатор сотрудника
     * @param workDate      дата работы
     */
    boolean existsByEmployeeIdAndWorkDate(Long employeeId, LocalDate workDate);

}
