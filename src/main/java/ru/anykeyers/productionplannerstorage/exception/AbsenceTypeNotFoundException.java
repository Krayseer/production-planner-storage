package ru.anykeyers.productionplannerstorage.exception;

import org.springframework.http.HttpStatus;

/**
 * Ошибка отсутствия типа отсутствия
 */
public class AbsenceTypeNotFoundException extends PlannerStorageResponseStatusException {

    public AbsenceTypeNotFoundException(Long absenceTypeId) {
        super(HttpStatus.NOT_FOUND, "Absence type not found with id: {0}", absenceTypeId);
    }

}
