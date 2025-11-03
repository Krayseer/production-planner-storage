package ru.anykeyers.productionplannerstorage.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.anykeyers.productionplannerstorage.controller.AbsenceTypeApi;
import ru.anykeyers.productionplannerstorage.controller.dto.AbsenceTypeDto;
import ru.anykeyers.productionplannerstorage.controller.request.AbsenceTypeDetails;
import ru.anykeyers.productionplannerstorage.service.AbsenceTypeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AbsenceTypeController implements AbsenceTypeApi {

    private final AbsenceTypeService absenceTypeService;

    @Override
    public List<AbsenceTypeDto> getAbsenceTypes() {
        return absenceTypeService.getAbsenceTypes();
    }

    @Override
    public AbsenceTypeDto getAbsenceTypeById(Long absenceTypeId) {
        return absenceTypeService.getAbsenceTypeById(absenceTypeId);
    }

    @Override
    public AbsenceTypeDto createAbsenceType(AbsenceTypeDetails absenceTypeDetails) {
        return absenceTypeService.createAbsenceType(absenceTypeDetails);
    }

    @Override
    public AbsenceTypeDto updateAbsenceType(Long absenceTypeId, AbsenceTypeDetails absenceTypeDetails) {
        return absenceTypeService.updateAbsenceType(absenceTypeId, absenceTypeDetails);
    }

}
