package ru.anykeyers.productionplannerstorage.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.anykeyers.productionplannerstorage.controller.dto.TeamDto;
import ru.anykeyers.productionplannerstorage.domain.Team;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TeamMapper extends BaseMapper<Team, TeamDto> {
}
