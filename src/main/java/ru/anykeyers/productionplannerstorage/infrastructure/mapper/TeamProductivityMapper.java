package ru.anykeyers.productionplannerstorage.infrastructure.mapper;

import org.mapstruct.Mapper;
import ru.anykeyers.productionplannerstorage.domain.DtoMapper;
import ru.anykeyers.productionplannerstorage.domain.dto.TeamProductivityDto;
import ru.anykeyers.productionplannerstorage.domain.model.TeamProductivity;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, uses = { TeamMapper.class, ProductMapper.class })
interface TeamProductivityMapper extends DtoMapper<TeamProductivity, TeamProductivityDto> {
}
