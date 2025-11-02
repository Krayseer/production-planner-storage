package ru.anykeyers.productionplannerstorage.controller.mapper;

import org.mapstruct.Mapper;
import ru.anykeyers.productionplannerstorage.config.CentralMapperConfig;
import ru.anykeyers.productionplannerstorage.controller.dto.TeamProductivityDto;
import ru.anykeyers.productionplannerstorage.domain.TeamProductivity;

@Mapper(config = CentralMapperConfig.class, uses = { TeamMapper.class, ProductMapper.class })
public interface TeamProductivityMapper extends BaseMapper<TeamProductivity, TeamProductivityDto> {
}
