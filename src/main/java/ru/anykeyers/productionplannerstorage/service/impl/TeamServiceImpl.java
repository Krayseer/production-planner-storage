package ru.anykeyers.productionplannerstorage.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.anykeyers.productionplannerstorage.domain.dto.TeamDto;
import ru.anykeyers.productionplannerstorage.domain.mapper.TeamMapper;
import ru.anykeyers.productionplannerstorage.domain.request.TeamDetails;
import ru.anykeyers.productionplannerstorage.domain.Team;
import ru.anykeyers.productionplannerstorage.domain.enums.TeamType;
import ru.anykeyers.productionplannerstorage.exception.TeamNotFoundException;
import ru.anykeyers.productionplannerstorage.repository.TeamRepository;
import ru.anykeyers.productionplannerstorage.service.TeamService;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamMapper teamMapper;
    private final TeamRepository teamRepository;

    @Override
    public List<TeamDto> getTeams() {
        return teamMapper.toDto(teamRepository.findAll());
    }

    @Override
    public TeamDto getTeam(Long teamId) {
        return teamMapper.toDto(getTeamEntity(teamId));
    }

    @Override
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

    @Override
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

    @Override
    public void deleteTeam(Long teamId) {
        teamRepository.deleteById(teamId);
    }

    private Team getTeamEntity(Long teamId) {
        return teamRepository.findById(teamId).orElseThrow(() -> new TeamNotFoundException(teamId));
    }

}
