package ru.anykeyers.productionplannerstorage.controller.mapper;

import org.mapstruct.Mapper;
import ru.anykeyers.productionplannerstorage.config.CentralMapperConfig;
import ru.anykeyers.productionplannerstorage.controller.dto.OptimizationParameterDto;
import ru.anykeyers.productionplannerstorage.domain.OptimizationParameter;

@Mapper(config = CentralMapperConfig.class)
public interface OptimizationParameterMapper extends BaseMapper<OptimizationParameter, OptimizationParameterDto> {
}
