package ru.anykeyers.productionplannerstorage.controller.mapper;

import org.mapstruct.Mapper;
import ru.anykeyers.productionplannerstorage.config.CentralMapperConfig;
import ru.anykeyers.productionplannerstorage.controller.dto.ProductionPlanDto;
import ru.anykeyers.productionplannerstorage.domain.ProductionPlan;

@Mapper(config = CentralMapperConfig.class, uses = { ProductMapper.class })
public interface ProductionPlanMapper extends BaseMapper<ProductionPlan, ProductionPlanDto> {
}
