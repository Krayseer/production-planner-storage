package ru.anykeyers.productionplannerstorage.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.anykeyers.productionplannerstorage.controller.dto.ProductDto;
import ru.anykeyers.productionplannerstorage.domain.Product;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper extends BaseMapper<Product, ProductDto> {
}
