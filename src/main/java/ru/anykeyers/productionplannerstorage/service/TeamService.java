package ru.anykeyers.productionplannerstorage.service;

import ru.anykeyers.productionplannerstorage.domain.dto.TeamDto;
import ru.anykeyers.productionplannerstorage.domain.request.TeamDetails;

import java.util.List;

/**
 * Сервис бригад
 */
public interface TeamService {

    /**
     * @return список бригад
     */
    List<TeamDto> getTeams();

    /**
     * Получить детали бригады
     *
     * @param teamId идентификатор бригады
     */
    TeamDto getTeam(Long teamId);

    /**
     * Создать бригаду
     *
     * @param teamDetails данные для создания бригады
     */
    TeamDto createTeam(TeamDetails teamDetails);

    /**
     * Обновить бригаду
     *
     * @param teamId        идентификатор бригады
     * @param teamDetails   обновленные данные о бригаде
     */
    TeamDto updateTeam(Long teamId, TeamDetails teamDetails);

    /**
     * Удалить бригаду
     *
     * @param teamId идентификатор бригады
     */
    void deleteTeam(Long teamId);

}
