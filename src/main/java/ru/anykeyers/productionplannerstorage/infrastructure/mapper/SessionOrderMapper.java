package ru.anykeyers.productionplannerstorage.infrastructure.mapper;

import org.mapstruct.Mapper;
import ru.anykeyers.productionplannerstorage.domain.DtoMapper;
import ru.anykeyers.productionplannerstorage.domain.model.SessionOrder;
import ru.anykeyers.productionplannerstorage.domain.dto.SessionOrderDto;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, uses = { ProductMapper.class })
interface SessionOrderMapper extends DtoMapper<SessionOrder, SessionOrderDto> {
}
