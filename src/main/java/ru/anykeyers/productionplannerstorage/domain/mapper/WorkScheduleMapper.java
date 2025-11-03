package ru.anykeyers.productionplannerstorage.domain.mapper;

import org.mapstruct.Mapper;
import ru.anykeyers.productionplannerstorage.config.CentralMapperConfig;
import ru.anykeyers.productionplannerstorage.domain.WorkSchedule;
import ru.anykeyers.productionplannerstorage.domain.dto.WorkScheduleDto;

@Mapper(config = CentralMapperConfig.class, uses = { EmployeeMapper.class, AbsenceTypeMapper.class })
public interface WorkScheduleMapper extends BaseMapper<WorkSchedule, WorkScheduleDto> {
}
