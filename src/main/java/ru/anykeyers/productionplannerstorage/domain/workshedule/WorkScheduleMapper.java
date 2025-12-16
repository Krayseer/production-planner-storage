package ru.anykeyers.productionplannerstorage.domain.workshedule;

import org.mapstruct.Mapper;
import ru.anykeyers.productionplannerstorage.domain.DtoMapper;
import ru.anykeyers.productionplannerstorage.domain.absencetype.AbsenceTypeMapper;
import ru.anykeyers.productionplannerstorage.domain.employee.EmployeeMapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, uses = { EmployeeMapper.class, AbsenceTypeMapper.class })
public interface WorkScheduleMapper extends DtoMapper<WorkSchedule, WorkScheduleDto> {
}
