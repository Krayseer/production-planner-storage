package ru.anykeyers.productionplannerstorage.domain.absencetype;

import org.springframework.http.HttpStatus;
import ru.anykeyers.productionplannerstorage.exception.PlannerStorageResponseStatusException;

/**
 * Ошибка отсутствия типа отсутствия
 */
public class AbsenceTypeNotFoundException extends PlannerStorageResponseStatusException {

    public AbsenceTypeNotFoundException(Long absenceTypeId) {
        super(HttpStatus.NOT_FOUND, "Absence type not found with id: {0}", absenceTypeId);
    }

}
