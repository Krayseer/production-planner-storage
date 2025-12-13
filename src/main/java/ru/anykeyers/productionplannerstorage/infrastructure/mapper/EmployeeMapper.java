package ru.anykeyers.productionplannerstorage.infrastructure.mapper;

import org.mapstruct.Mapper;
import ru.anykeyers.productionplannerstorage.domain.DtoMapper;
import ru.anykeyers.productionplannerstorage.domain.dto.EmployeeDto;
import ru.anykeyers.productionplannerstorage.domain.model.Employee;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, uses = { TeamMapper.class })
interface EmployeeMapper extends DtoMapper<Employee, EmployeeDto> {
}
