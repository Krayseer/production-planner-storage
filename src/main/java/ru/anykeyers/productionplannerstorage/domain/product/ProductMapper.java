package ru.anykeyers.productionplannerstorage.domain.product;

import org.mapstruct.Mapper;
import ru.anykeyers.productionplannerstorage.domain.DtoMapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface ProductMapper extends DtoMapper<Product, ProductDto> {
}
