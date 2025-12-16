package ru.anykeyers.productionplannerstorage.domain.absencetype;

import org.mapstruct.Mapper;
import ru.anykeyers.productionplannerstorage.domain.DtoMapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface AbsenceTypeMapper extends DtoMapper<AbsenceType, AbsenceTypeDto> {
}
