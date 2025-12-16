package ru.anykeyers.productionplannerstorage.domain.session.order;

import org.mapstruct.Mapper;
import ru.anykeyers.productionplannerstorage.domain.DtoMapper;
import ru.anykeyers.productionplannerstorage.domain.product.ProductMapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, uses = { ProductMapper.class })
public interface SessionOrderMapper extends DtoMapper<SessionOrder, SessionOrderDto> {
}
