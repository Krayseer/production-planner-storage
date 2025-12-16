package ru.anykeyers.productionplannerstorage.domain.workshedule;

import org.springframework.http.HttpStatus;
import ru.anykeyers.productionplannerstorage.exception.PlannerStorageResponseStatusException;

import java.time.LocalDate;

/**
 * Ошибка уникальности комбинации (employeeId, workDate) табеля рабочего времени
 */
public class WorkScheduleNotUniqueException extends PlannerStorageResponseStatusException {

    public WorkScheduleNotUniqueException(Long employeeId, LocalDate workDate) {
        super(HttpStatus.BAD_REQUEST, "Work schedule with employee_id={0}, work_date={1} already exists", employeeId, workDate);
    }

}
