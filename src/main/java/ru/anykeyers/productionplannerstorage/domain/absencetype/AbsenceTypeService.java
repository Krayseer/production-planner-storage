package ru.anykeyers.productionplannerstorage.domain.absencetype;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.anykeyers.productionplannerstorage.domain.DtoMapper;

import java.util.List;

/**
 * Сервис типов отсутствий
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AbsenceTypeService {

    private final AbsenceTypeRepository absenceTypeRepository;
    private final DtoMapper<AbsenceType, AbsenceTypeDto> absenceTypeMapper;

    /**
     * @return список типов отсутствий
     */
    public List<AbsenceTypeDto> getAbsenceTypes() {
        return absenceTypeMapper.toDto(absenceTypeRepository.findAll());
    }

    /**
     * Детали типа отсутствия
     *
     * @param absenceTypeId идентификатор типа отсутствия
     */
    public AbsenceTypeDto getAbsenceTypeById(Long absenceTypeId) {
        return absenceTypeMapper.toDto(getAbsenceType(absenceTypeId));
    }

    /**
     * Создать тип отсутствия
     *
     * @param absenceTypeDetails данные о типе отсутствия
     */
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

    /**
     * Обновить данные о типе отсутствия
     *
     * @param absenceTypeId         идентификатор типа отсутствия
     * @param absenceTypeDetails    обновленные данные о типе отсутствия
     */
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
