package ru.anykeyers.productionplannerstorage.domain.workshedule;

import org.springframework.http.HttpStatus;
import ru.anykeyers.productionplannerstorage.exception.PlannerStorageResponseStatusException;

/**
 * Ошибка отсутствия табеля рабочего времени
 */
public class WorkScheduleNotFoundException extends PlannerStorageResponseStatusException {

    public WorkScheduleNotFoundException(Long workScheduleId) {
        super(HttpStatus.NOT_FOUND, "Work schedule not found with id: {0}", workScheduleId);
    }

}
