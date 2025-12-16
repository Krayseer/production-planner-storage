package ru.anykeyers.productionplannerstorage.domain.team;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.anykeyers.productionplannerstorage.domain.DtoMapper;

import java.util.List;

/**
 * Сервис бригад
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final DtoMapper<Team, TeamDto> teamMapper;

    /**
     * @return список бригад
     */
    public List<TeamDto> getTeams() {
        return teamMapper.toDto(teamRepository.findAll());
    }

    /**
     * Получить детали бригады
     *
     * @param teamId идентификатор бригады
     */
    public TeamDto getTeam(Long teamId) {
        return teamMapper.toDto(getTeamEntity(teamId));
    }

    /**
     * Создать бригаду
     *
     * @param teamDetails данные для создания бригады
     */
    public TeamDto createTeam(TeamDetails teamDetails) {
        Team team = Team.builder()
                .name(teamDetails.name())
                .teamType(TeamType.valueOf(teamDetails.teamType().toUpperCase()))
                .employeeCount(teamDetails.employeeCount())
                .monthlyHours(teamDetails.monthlyHours())
                .maxDailyHours(teamDetails.maxDailyHours())
                .active(teamDetails.active())
                .build();
        Team savedTeam = teamRepository.save(team);
        log.info("Created team: {}", savedTeam);
        return teamMapper.toDto(savedTeam);
    }

    /**
     * Обновить бригаду
     *
     * @param teamId        идентификатор бригады
     * @param teamDetails   обновленные данные о бригаде
     */
    public TeamDto updateTeam(Long teamId, TeamDetails teamDetails) {
        Team team = getTeamEntity(teamId);
        team.setName(teamDetails.name());
        team.setTeamType(TeamType.valueOf(teamDetails.teamType().toUpperCase()));
        team.setEmployeeCount(teamDetails.employeeCount());
        team.setMonthlyHours(teamDetails.monthlyHours());
        team.setMaxDailyHours(teamDetails.maxDailyHours());
        team.setActive(teamDetails.active());
        Team updatedTeam = teamRepository.save(team);
        log.info("Updated team: {}", updatedTeam);
        return teamMapper.toDto(updatedTeam);

    }

    /**
     * Удалить бригаду
     *
     * @param teamId идентификатор бригады
     */
    public void deleteTeam(Long teamId) {
        teamRepository.deleteById(teamId);
    }

    private Team getTeamEntity(Long teamId) {
        return teamRepository.findById(teamId).orElseThrow(() -> new TeamNotFoundException(teamId));
    }

}
