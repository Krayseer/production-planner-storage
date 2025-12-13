package ru.anykeyers.productionplannerstorage.infrastructure.mapper;

import org.mapstruct.Mapper;
import ru.anykeyers.productionplannerstorage.domain.DtoMapper;
import ru.anykeyers.productionplannerstorage.domain.dto.AbsenceTypeDto;
import ru.anykeyers.productionplannerstorage.domain.model.AbsenceType;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
interface AbsenceTypeMapper extends DtoMapper<AbsenceType, AbsenceTypeDto> {
}
