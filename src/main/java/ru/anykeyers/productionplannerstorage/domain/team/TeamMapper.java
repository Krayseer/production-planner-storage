package ru.anykeyers.productionplannerstorage.domain.team;

import org.mapstruct.Mapper;
import ru.anykeyers.productionplannerstorage.domain.DtoMapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface TeamMapper extends DtoMapper<Team, TeamDto> {
}
