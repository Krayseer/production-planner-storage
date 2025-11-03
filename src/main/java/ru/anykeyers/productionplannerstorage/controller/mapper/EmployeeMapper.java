package ru.anykeyers.productionplannerstorage.controller.mapper;

import org.mapstruct.Mapper;
import ru.anykeyers.productionplannerstorage.config.CentralMapperConfig;
import ru.anykeyers.productionplannerstorage.controller.dto.EmployeeDto;
import ru.anykeyers.productionplannerstorage.domain.Employee;

@Mapper(config = CentralMapperConfig.class, uses = { TeamMapper.class })
public interface EmployeeMapper extends BaseMapper<Employee, EmployeeDto> {
}
