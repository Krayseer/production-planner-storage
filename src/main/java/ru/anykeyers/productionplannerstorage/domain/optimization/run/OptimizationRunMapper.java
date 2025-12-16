package ru.anykeyers.productionplannerstorage.domain.optimization.run;

import org.mapstruct.Mapper;
import ru.anykeyers.productionplannerstorage.domain.DtoMapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface OptimizationRunMapper extends DtoMapper<OptimizationRun, OptimizationRunDto> {
}
