package ru.anykeyers.productionplannerstorage.domain.mapper;

import org.mapstruct.Mapper;
import ru.anykeyers.productionplannerstorage.config.CentralMapperConfig;
import ru.anykeyers.productionplannerstorage.domain.SessionOrder;
import ru.anykeyers.productionplannerstorage.domain.dto.SessionOrderDto;

@Mapper(config = CentralMapperConfig.class, uses = { ProductMapper.class })
public interface SessionOrderMapper extends BaseMapper<SessionOrder, SessionOrderDto> {
}
