package ru.anykeyers.productionplannerstorage.controller.mapper;

import org.mapstruct.Mapper;
import ru.anykeyers.productionplannerstorage.config.CentralMapperConfig;
import ru.anykeyers.productionplannerstorage.controller.dto.ProductDto;
import ru.anykeyers.productionplannerstorage.domain.Product;

@Mapper(config = CentralMapperConfig.class)
public interface ProductMapper extends BaseMapper<Product, ProductDto> {
}
