package ru.anykeyers.productionplannerstorage.domain.mapper;

import org.mapstruct.Mapper;
import ru.anykeyers.productionplannerstorage.config.CentralMapperConfig;
import ru.anykeyers.productionplannerstorage.domain.ProductionSession;
import ru.anykeyers.productionplannerstorage.domain.dto.ProductionSessionDto;

@Mapper(config = CentralMapperConfig.class, uses = { SessionOrderMapper.class })
public interface ProductionSessionMapper extends BaseMapper<ProductionSession, ProductionSessionDto> {
}
