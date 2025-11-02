package ru.anykeyers.productionplannerstorage.controller.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.anykeyers.productionplannerstorage.controller.dto.OptimizationParameterDto;
import ru.anykeyers.productionplannerstorage.domain.OptimizationParameter;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OptimizationParameterMapper extends BaseMapper<OptimizationParameter, OptimizationParameterDto> {
}
