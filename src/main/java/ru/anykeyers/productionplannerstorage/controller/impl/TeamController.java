package ru.anykeyers.productionplannerstorage.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.anykeyers.productionplannerstorage.controller.TeamApi;
import ru.anykeyers.productionplannerstorage.controller.dto.TeamDto;
import ru.anykeyers.productionplannerstorage.controller.request.TeamDetails;
import ru.anykeyers.productionplannerstorage.service.TeamService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TeamController implements TeamApi {

    private final TeamService teamService;

    @Override
    public List<TeamDto> getTeams() {
        return teamService.getTeams();
    }

    @Override
    public TeamDto getTeam(Long id) {
        return teamService.getTeam(id);
    }

    @Override
    public TeamDto createTeam(TeamDetails teamDetails) {
        return teamService.createTeam(teamDetails);
    }

    @Override
    public TeamDto updateTeam(Long id, TeamDetails teamDetails) {
        return teamService.updateTeam(id, teamDetails);
    }

    @Override
    public void deleteTeam(Long id) {
        teamService.deleteTeam(id);
    }

}
