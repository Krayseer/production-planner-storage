package ru.anykeyers.productionplannerstorage.config;

import org.mapstruct.MapperConfig;
import org.mapstruct.MappingConstants;

/**
 * Общая конфигурация {@link org.mapstruct.Mapper}
 */
@MapperConfig(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CentralMapperConfig {
}
