package ru.anykeyers.productionplannerstorage.exception;

import org.springframework.http.HttpStatus;

/**
 * Ошибка отсутствия графика слесарного участка
 */
public class AssemblyScheduleNotFoundException extends PlannerStorageResponseStatusException {

    public AssemblyScheduleNotFoundException(Long assemblyScheduleId) {
        super(HttpStatus.NOT_FOUND, "Assembly schedule not found with id: {0}", assemblyScheduleId);
    }

}
