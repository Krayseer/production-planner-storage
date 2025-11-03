package ru.anykeyers.productionplannerstorage.service;

import ru.anykeyers.productionplannerstorage.domain.dto.AbsenceTypeDto;
import ru.anykeyers.productionplannerstorage.domain.request.AbsenceTypeDetails;
import ru.anykeyers.productionplannerstorage.exception.AbsenceTypeNotFoundException;
import ru.anykeyers.productionplannerstorage.exception.AbsenceTypeNotUniqueCodeException;

import java.util.List;

/**
 * Сервис типов отсутствий
 */
public interface AbsenceTypeService {

    /**
     * @return список типов отсутствий
     */
    List<AbsenceTypeDto> getAbsenceTypes();

    /**
     * Детали типа отсутствия
     *
     * @param absenceTypeId идентификатор типа отсутствия
     */
    AbsenceTypeDto getAbsenceTypeById(Long absenceTypeId) throws AbsenceTypeNotFoundException;

    /**
     * Создать тип отсутствия
     *
     * @param absenceTypeDetails данные о типе отсутствия
     */
    AbsenceTypeDto createAbsenceType(AbsenceTypeDetails absenceTypeDetails) throws AbsenceTypeNotUniqueCodeException;

    /**
     * Обновить данные о типе отсутствия
     *
     * @param absenceTypeId         идентификатор типа отсутствия
     * @param absenceTypeDetails    обновленные данные о типе отсутствия
     */
    AbsenceTypeDto updateAbsenceType(Long absenceTypeId, AbsenceTypeDetails absenceTypeDetails)
            throws AbsenceTypeNotFoundException, AbsenceTypeNotUniqueCodeException;

}
