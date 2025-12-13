package ru.anykeyers.productionplannerstorage.infrastructure.mapper;

import org.mapstruct.Mapper;
import ru.anykeyers.productionplannerstorage.domain.DtoMapper;
import ru.anykeyers.productionplannerstorage.domain.model.ProductionSession;
import ru.anykeyers.productionplannerstorage.domain.dto.ProductionSessionDto;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, uses = { SessionOrderMapper.class })
interface ProductionSessionMapper extends DtoMapper<ProductionSession, ProductionSessionDto> {
}
