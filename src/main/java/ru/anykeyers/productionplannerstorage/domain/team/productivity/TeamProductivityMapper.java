package ru.anykeyers.productionplannerstorage.domain.team.productivity;

import org.mapstruct.Mapper;
import ru.anykeyers.productionplannerstorage.domain.DtoMapper;
import ru.anykeyers.productionplannerstorage.domain.product.ProductMapper;
import ru.anykeyers.productionplannerstorage.domain.team.TeamMapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, uses = { TeamMapper.class, ProductMapper.class })
interface TeamProductivityMapper extends DtoMapper<TeamProductivity, TeamProductivityDto> {
}
