package ru.anykeyers.productionplannerstorage.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.anykeyers.productionplannerstorage.controller.TeamProductivityApi;
import ru.anykeyers.productionplannerstorage.domain.dto.TeamProductivityDto;
import ru.anykeyers.productionplannerstorage.domain.request.TeamProductivityDetails;
import ru.anykeyers.productionplannerstorage.service.TeamProductivityService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TeamProductivityController implements TeamProductivityApi {

    private final TeamProductivityService teamProductivityService;

    @Override
    public List<TeamProductivityDto> getAllTeamProductivity() {
        return teamProductivityService.getAllTeamProductivity();
    }

    @Override
    public List<TeamProductivityDto> getTeamProductivityByTeamId(Long teamId) {
        return teamProductivityService.getTeamProductivityByTeamId(teamId);
    }

    @Override
    public List<TeamProductivityDto> getTeamProductivityByProductId(Long productId) {
        return teamProductivityService.getTeamProductivityByProductId(productId);
    }

    @Override
    public TeamProductivityDto createTeamProductivity(TeamProductivityDetails teamProductivityDetails) {
        return teamProductivityService.createTeamProductivity(teamProductivityDetails);
    }

    @Override
    public TeamProductivityDto updateTeamProductivity(Long id, TeamProductivityDetails teamProductivityDetails) {
        return teamProductivityService.updateTeamProductivity(id, teamProductivityDetails);
    }

    @Override
    public void deleteTeamProductivity(Long id) {
        teamProductivityService.deleteTeamProductivity(id);
    }

}
