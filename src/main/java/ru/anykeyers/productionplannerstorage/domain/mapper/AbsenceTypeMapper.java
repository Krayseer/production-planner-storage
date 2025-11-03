package ru.anykeyers.productionplannerstorage.domain.mapper;

import org.mapstruct.Mapper;
import ru.anykeyers.productionplannerstorage.config.CentralMapperConfig;
import ru.anykeyers.productionplannerstorage.domain.dto.AbsenceTypeDto;
import ru.anykeyers.productionplannerstorage.domain.AbsenceType;

@Mapper(config = CentralMapperConfig.class)
public interface AbsenceTypeMapper extends BaseMapper<AbsenceType, AbsenceTypeDto> {
}
