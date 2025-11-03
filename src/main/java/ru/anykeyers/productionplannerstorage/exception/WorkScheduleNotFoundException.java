package ru.anykeyers.productionplannerstorage.exception;

import org.springframework.http.HttpStatus;

/**
 * Ошибка отсутствия табеля рабочего времени
 */
public class WorkScheduleNotFoundException extends PlannerStorageResponseStatusException {

    public WorkScheduleNotFoundException(Long workScheduleId) {
        super(HttpStatus.NOT_FOUND, "Work schedule not found with id: {0}", workScheduleId);
    }

}
