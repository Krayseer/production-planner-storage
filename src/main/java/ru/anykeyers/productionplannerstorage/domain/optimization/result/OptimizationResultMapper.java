package ru.anykeyers.productionplannerstorage.domain.optimization.result;

import org.mapstruct.Mapper;
import ru.anykeyers.productionplannerstorage.domain.DtoMapper;
import ru.anykeyers.productionplannerstorage.domain.optimization.run.OptimizationRunMapper;
import ru.anykeyers.productionplannerstorage.domain.product.ProductDto;
import ru.anykeyers.productionplannerstorage.domain.session.ProductionSessionMapper;
import ru.anykeyers.productionplannerstorage.domain.team.TeamMapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, uses = { OptimizationRunMapper.class, ProductionSessionMapper.class, TeamMapper.class, ProductDto.class })
public interface OptimizationResultMapper extends DtoMapper<OptimizationResult, OptimizationResultDto> {
}
