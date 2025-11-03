package ru.anykeyers.productionplannerstorage.service;

import ru.anykeyers.productionplannerstorage.domain.dto.WorkScheduleDto;
import ru.anykeyers.productionplannerstorage.domain.request.WorkScheduleDetails;
import ru.anykeyers.productionplannerstorage.exception.AbsenceTypeNotFoundException;
import ru.anykeyers.productionplannerstorage.exception.EmployeeNotFoundException;
import ru.anykeyers.productionplannerstorage.exception.WorkScheduleNotFoundException;
import ru.anykeyers.productionplannerstorage.exception.WorkScheduleNotUniqueException;

import java.util.List;

/**
 * Сервис управления табелем рабочего времени
 */
public interface WorkScheduleService {

    /**
     * @return записи табеля
     */
    List<WorkScheduleDto> getWorkSchedules();

    /**
     * Создать запись табеля рабочего времени
     *
     * @param workScheduleDetails данные для создания табеля рабочего времени
     */
    WorkScheduleDto createWorkSchedule(WorkScheduleDetails workScheduleDetails)
            throws WorkScheduleNotUniqueException, EmployeeNotFoundException, AbsenceTypeNotFoundException;

    /**
     * Обновить запись табеля рабочего времени
     *
     * @param workScheduleId        идентификатор записи табеля рабочего времени
     * @param workScheduleDetails   обновленные данные табеля рабочего времени
     */
    WorkScheduleDto updateWorkSchedule(Long workScheduleId, WorkScheduleDetails workScheduleDetails)
            throws WorkScheduleNotFoundException, WorkScheduleNotUniqueException, EmployeeNotFoundException, AbsenceTypeNotFoundException;

}
