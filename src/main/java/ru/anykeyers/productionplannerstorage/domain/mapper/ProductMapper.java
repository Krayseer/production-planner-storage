package ru.anykeyers.productionplannerstorage.domain.mapper;

import org.mapstruct.Mapper;
import ru.anykeyers.productionplannerstorage.config.CentralMapperConfig;
import ru.anykeyers.productionplannerstorage.domain.dto.ProductDto;
import ru.anykeyers.productionplannerstorage.domain.Product;

@Mapper(config = CentralMapperConfig.class)
public interface ProductMapper extends BaseMapper<Product, ProductDto> {
}
