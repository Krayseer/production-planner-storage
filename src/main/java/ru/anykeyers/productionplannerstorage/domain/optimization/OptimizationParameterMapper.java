package ru.anykeyers.productionplannerstorage.domain.optimization;

import org.mapstruct.Mapper;
import ru.anykeyers.productionplannerstorage.domain.DtoMapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface OptimizationParameterMapper extends DtoMapper<OptimizationRun, OptimizationRunDto> {
}
