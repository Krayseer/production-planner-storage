package ru.anykeyers.productionplannerstorage.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.anykeyers.productionplannerstorage.controller.dto.ProductionPlanDto;
import ru.anykeyers.productionplannerstorage.domain.ProductionPlan;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = { ProductionPlanMapper.class })
public interface ProductionPlanMapper extends BaseMapper<ProductionPlan, ProductionPlanDto> {
}
