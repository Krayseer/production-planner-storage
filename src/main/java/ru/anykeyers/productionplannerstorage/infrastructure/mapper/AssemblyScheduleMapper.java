package ru.anykeyers.productionplannerstorage.infrastructure.mapper;

import org.mapstruct.Mapper;
import ru.anykeyers.productionplannerstorage.domain.DtoMapper;
import ru.anykeyers.productionplannerstorage.domain.model.AssemblySchedule;
import ru.anykeyers.productionplannerstorage.domain.dto.AssemblyScheduleDto;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, uses = { TeamMapper.class, ProductMapper.class })
interface AssemblyScheduleMapper extends DtoMapper<AssemblySchedule, AssemblyScheduleDto> {
}
