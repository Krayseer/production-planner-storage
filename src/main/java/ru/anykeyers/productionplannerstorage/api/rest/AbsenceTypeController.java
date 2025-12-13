package ru.anykeyers.productionplannerstorage.api.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.anykeyers.productionplannerstorage.api.AbsenceTypeApi;
import ru.anykeyers.productionplannerstorage.domain.dto.AbsenceTypeDto;
import ru.anykeyers.productionplannerstorage.domain.dto.request.AbsenceTypeDetails;
import ru.anykeyers.productionplannerstorage.domain.service.AbsenceTypeService;

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
    @ResponseStatus(HttpStatus.CREATED)
    public AbsenceTypeDto createAbsenceType(@RequestBody @Valid AbsenceTypeDetails absenceTypeDetails) {
        return absenceTypeService.createAbsenceType(absenceTypeDetails);
    }

    @Override
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AbsenceTypeDto updateAbsenceType(@PathVariable("id") Long absenceTypeId,
                                            @RequestBody @Valid AbsenceTypeDetails absenceTypeDetails) {
        return absenceTypeService.updateAbsenceType(absenceTypeId, absenceTypeDetails);
    }

}
