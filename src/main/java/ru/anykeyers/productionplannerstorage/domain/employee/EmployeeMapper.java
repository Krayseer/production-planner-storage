package ru.anykeyers.productionplannerstorage.domain.employee;

import org.mapstruct.Mapper;
import ru.anykeyers.productionplannerstorage.domain.DtoMapper;
import ru.anykeyers.productionplannerstorage.domain.team.TeamMapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, uses = { TeamMapper.class })
public interface EmployeeMapper extends DtoMapper<Employee, EmployeeDto> {
}
