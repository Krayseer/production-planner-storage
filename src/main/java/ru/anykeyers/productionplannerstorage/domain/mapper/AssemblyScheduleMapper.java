package ru.anykeyers.productionplannerstorage.domain.mapper;

import org.mapstruct.Mapper;
import ru.anykeyers.productionplannerstorage.config.CentralMapperConfig;
import ru.anykeyers.productionplannerstorage.domain.AssemblySchedule;
import ru.anykeyers.productionplannerstorage.domain.dto.AssemblyScheduleDto;

@Mapper(config = CentralMapperConfig.class, uses = { TeamMapper.class, ProductMapper.class })
public interface AssemblyScheduleMapper extends BaseMapper<AssemblySchedule, AssemblyScheduleDto> {
}
