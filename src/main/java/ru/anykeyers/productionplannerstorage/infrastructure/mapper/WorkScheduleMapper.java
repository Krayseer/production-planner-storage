package ru.anykeyers.productionplannerstorage.infrastructure.mapper;

import org.mapstruct.Mapper;
import ru.anykeyers.productionplannerstorage.domain.DtoMapper;
import ru.anykeyers.productionplannerstorage.domain.model.WorkSchedule;
import ru.anykeyers.productionplannerstorage.domain.dto.WorkScheduleDto;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, uses = { EmployeeMapper.class, AbsenceTypeMapper.class })
interface WorkScheduleMapper extends DtoMapper<WorkSchedule, WorkScheduleDto> {
}
