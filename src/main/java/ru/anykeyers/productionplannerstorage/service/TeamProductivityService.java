package ru.anykeyers.productionplannerstorage.service;

import ru.anykeyers.productionplannerstorage.controller.dto.TeamProductivityDto;
import ru.anykeyers.productionplannerstorage.controller.request.TeamProductivityDetails;
import ru.anykeyers.productionplannerstorage.exception.ProductNotFoundException;
import ru.anykeyers.productionplannerstorage.exception.TeamNotFoundException;
import ru.anykeyers.productionplannerstorage.exception.TeamProductivityNotFoundException;
import ru.anykeyers.productionplannerstorage.exception.TeamProductivityNotUniqueException;

import java.util.List;

/**
 * Сервис матриц производительности бригад
 */
public interface TeamProductivityService {

    /**
     * @return список записей производительности бригад
     */
    List<TeamProductivityDto> getAllTeamProductivity();

    /**
     * Получить записи производительности бригады
     *
     * @param teamId идентификатор бригады
     */
    List<TeamProductivityDto> getTeamProductivityByTeamId(Long teamId);

    /**
     * Получить записи производительности бригады
     *
     * @param productId идентификатор продукта
     */
    List<TeamProductivityDto> getTeamProductivityByProductId(Long productId);

    /**
     * Создать запись производительности бригады
     *
     * @param teamProductivityDetails данные о производительности бригады
     */
    TeamProductivityDto createTeamProductivity(TeamProductivityDetails teamProductivityDetails)
            throws TeamNotFoundException, ProductNotFoundException, TeamProductivityNotUniqueException;

    /**
     * Обновить запись производительности бригады
     *
     * @param teamProductivityId        идентификатор записи производительности бригады
     * @param teamProductivityDetails   обновленные данные о записи производительности бригады
     */
    TeamProductivityDto updateTeamProductivity(Long teamProductivityId, TeamProductivityDetails teamProductivityDetails)
            throws TeamProductivityNotFoundException, TeamNotFoundException, ProductNotFoundException, TeamProductivityNotUniqueException;

    /**
     * Удалить запись производительности бригады
     *
     * @param teamProductivityId идентификатор записи производительности бригады
     */
    void deleteTeamProductivity(Long teamProductivityId);

}
