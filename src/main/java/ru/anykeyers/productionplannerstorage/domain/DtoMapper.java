package ru.anykeyers.productionplannerstorage.domain;

import java.util.List;

/**
 * Базовый маппер для преобразования сущностей в DTO
 *
 * @param <Entity>  класс сущности
 * @param <Dto>     класс DTO
 */
public interface DtoMapper<Entity, Dto> {

    /**
     * Преобразует одну сущность в DTO
     */
    Dto toDto(Entity entity);

    /**
     * Преобразует список сущностей в список DTO
     */
    List<Dto> toDto(List<Entity> entities);

}

