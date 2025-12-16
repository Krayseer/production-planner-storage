package ru.anykeyers.productionplannerstorage.domain.session;

import org.mapstruct.Mapper;
import ru.anykeyers.productionplannerstorage.domain.DtoMapper;
import ru.anykeyers.productionplannerstorage.domain.session.order.SessionOrderMapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, uses = { SessionOrderMapper.class })
public interface ProductionSessionMapper extends DtoMapper<ProductionSession, ProductionSessionDto> {
}
