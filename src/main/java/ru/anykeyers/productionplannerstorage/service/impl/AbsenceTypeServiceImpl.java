package ru.anykeyers.productionplannerstorage.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.anykeyers.productionplannerstorage.controller.dto.AbsenceTypeDto;
import ru.anykeyers.productionplannerstorage.controller.mapper.AbsenceTypeMapper;
import ru.anykeyers.productionplannerstorage.controller.request.AbsenceTypeDetails;
import ru.anykeyers.productionplannerstorage.domain.AbsenceType;
import ru.anykeyers.productionplannerstorage.exception.AbsenceTypeNotFoundException;
import ru.anykeyers.productionplannerstorage.exception.AbsenceTypeNotUniqueCodeException;
import ru.anykeyers.productionplannerstorage.repository.AbsenceTypeRepository;
import ru.anykeyers.productionplannerstorage.service.AbsenceTypeService;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AbsenceTypeServiceImpl implements AbsenceTypeService {

    private final AbsenceTypeMapper absenceTypeMapper;
    private final AbsenceTypeRepository absenceTypeRepository;

    @Override
    public List<AbsenceTypeDto> getAbsenceTypes() {
        return absenceTypeMapper.toDto(absenceTypeRepository.findAll());
    }

    @Override
    public AbsenceTypeDto getAbsenceTypeById(Long absenceTypeId) {
        return absenceTypeMapper.toDto(getAbsenceType(absenceTypeId));
    }

    @Override
    public AbsenceTypeDto createAbsenceType(AbsenceTypeDetails absenceTypeDetails) {
        checkCodeUnique(absenceTypeDetails.code());
        AbsenceType absenceType = AbsenceType.builder()
                .code(absenceTypeDetails.code())
                .name(absenceTypeDetails.name())
                .impactFactor(absenceTypeDetails.impactFactor())
                .active(absenceTypeDetails.active())
                .build();
        AbsenceType savedAbsenceType = absenceTypeRepository.save(absenceType);
        log.info("Saved absence type: {}", savedAbsenceType);
        return absenceTypeMapper.toDto(savedAbsenceType);
    }

    @Override
    public AbsenceTypeDto updateAbsenceType(Long absenceTypeId, AbsenceTypeDetails absenceTypeDetails) {
        AbsenceType absenceType = getAbsenceType(absenceTypeId);
        checkCodeUnique(absenceTypeDetails.code());
        absenceType.setCode(absenceTypeDetails.code());
        absenceType.setName(absenceTypeDetails.name());
        absenceType.setImpactFactor(absenceTypeDetails.impactFactor());
        absenceType.setActive(absenceTypeDetails.active());
        AbsenceType updatedAbsenceType = absenceTypeRepository.save(absenceType);
        log.info("Updated absence type: {}", updatedAbsenceType);
        return absenceTypeMapper.toDto(updatedAbsenceType);
    }

    private AbsenceType getAbsenceType(Long absenceTypeId) {
        return absenceTypeRepository.findById(absenceTypeId)
                .orElseThrow(() -> new AbsenceTypeNotFoundException(absenceTypeId));
    }

    private void checkCodeUnique(String code) {
        if (absenceTypeRepository.existsAbsenceTypeByCode(code)) {
            throw new AbsenceTypeNotUniqueCodeException(code);
        }
    }

}
