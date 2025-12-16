package ru.anykeyers.productionplannerstorage.api.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.anykeyers.productionplannerstorage.api.AbsenceTypeApi;
import ru.anykeyers.productionplannerstorage.domain.absencetype.AbsenceTypeDto;
import ru.anykeyers.productionplannerstorage.domain.absencetype.AbsenceTypeDetails;
import ru.anykeyers.productionplannerstorage.domain.absencetype.AbsenceTypeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(RestControllerPath.ABSENCE_TYPES)
class AbsenceTypeController implements AbsenceTypeApi {

    private final AbsenceTypeService absenceTypeService;

    @Override
    @GetMapping
    public List<AbsenceTypeDto> getAbsenceTypes() {
        return absenceTypeService.getAbsenceTypes();
    }

    @Override
    @GetMapping("/{id}")
    public AbsenceTypeDto getAbsenceTypeById(@PathVariable("id") Long absenceTypeId) {
        return absenceTypeService.getAbsenceTypeById(absenceTypeId);
    }

    @Override
    @PostMapping
    public AbsenceTypeDto createAbsenceType(@RequestBody @Valid AbsenceTypeDetails absenceTypeDetails) {
        return absenceTypeService.createAbsenceType(absenceTypeDetails);
    }

    @Override
    @PutMapping("/{id}")
    public AbsenceTypeDto updateAbsenceType(@PathVariable("id") Long absenceTypeId,
                                            @RequestBody @Valid AbsenceTypeDetails absenceTypeDetails) {
        return absenceTypeService.updateAbsenceType(absenceTypeId, absenceTypeDetails);
    }

}
