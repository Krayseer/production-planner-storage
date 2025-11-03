package ru.anykeyers.productionplannerstorage.domain.mapper;

import java.util.List;

/**
 * Базовый маппер для преобразования сущностей в DTO<p/>
 * Логика по преобразованию запросов в сущности должна быть реализована в сервисах
 *
 * @param <E> класс сущности
 * @param <D> класс DTO
 */
public interface BaseMapper<E, D> {

    /**
     * Преобразует одну сущность в DTO
     *
     * @param entity сущность для преобразования
     * @return соответствующий DTO
     */
    D toDto(E entity);

    /**
     * Преобразует список сущностей в список DTO
     *
     * @param entities список сущностей
     * @return список соответствующих DTO
     */
    List<D> toDto(List<E> entities);

}

