package ru.anykeyers.productionplannerstorage.domain.mapper;

import org.mapstruct.Mapper;
import ru.anykeyers.productionplannerstorage.config.CentralMapperConfig;
import ru.anykeyers.productionplannerstorage.domain.dto.TeamDto;
import ru.anykeyers.productionplannerstorage.domain.Team;

@Mapper(config = CentralMapperConfig.class)
public interface TeamMapper extends BaseMapper<Team, TeamDto> {
}
