package ru.anykeyers.productionplannerstorage.domain.mapper;

import org.mapstruct.Mapper;
import ru.anykeyers.productionplannerstorage.config.CentralMapperConfig;
import ru.anykeyers.productionplannerstorage.domain.dto.OptimizationParameterDto;
import ru.anykeyers.productionplannerstorage.domain.OptimizationParameter;

@Mapper(config = CentralMapperConfig.class)
public interface OptimizationParameterMapper extends BaseMapper<OptimizationParameter, OptimizationParameterDto> {
}
