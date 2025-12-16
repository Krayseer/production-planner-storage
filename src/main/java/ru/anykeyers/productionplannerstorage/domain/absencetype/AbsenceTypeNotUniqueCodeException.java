package ru.anykeyers.productionplannerstorage.domain.absencetype;

import org.springframework.http.HttpStatus;
import ru.anykeyers.productionplannerstorage.exception.PlannerStorageResponseStatusException;

/**
 * Ошибка уникальности {@link AbsenceType#getCode() кода отсутствия}
 */
public class AbsenceTypeNotUniqueCodeException extends PlannerStorageResponseStatusException {

    public AbsenceTypeNotUniqueCodeException(String code) {
        super(HttpStatus.BAD_REQUEST, "Absence type already exists with code {0}", code);
    }

}
