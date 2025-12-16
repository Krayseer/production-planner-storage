package ru.anykeyers.productionplannerstorage.api.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.anykeyers.productionplannerstorage.api.TeamProductivityApi;
import ru.anykeyers.productionplannerstorage.domain.team.productivity.TeamProductivityDto;
import ru.anykeyers.productionplannerstorage.domain.team.productivity.TeamProductivityDetails;
import ru.anykeyers.productionplannerstorage.domain.team.productivity.TeamProductivityService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(RestControllerPath.TEAM_PRODUCTIVITY)
class TeamProductivityController implements TeamProductivityApi {

    private final TeamProductivityService teamProductivityService;

    @Override
    @GetMapping
    public List<TeamProductivityDto> getAllTeamProductivity() {
        return teamProductivityService.getAllTeamProductivity();
    }

    @Override
    @GetMapping("/team/{teamId}")
    public List<TeamProductivityDto> getTeamProductivityByTeamId(@PathVariable Long teamId) {
        return teamProductivityService.getTeamProductivityByTeamId(teamId);
    }

    @Override
    @GetMapping("/product/{productId}")
    public List<TeamProductivityDto> getTeamProductivityByProductId(@PathVariable Long productId) {
        return teamProductivityService.getTeamProductivityByProductId(productId);
    }

    @Override
    @PostMapping
    public TeamProductivityDto createTeamProductivity(@RequestBody @Valid TeamProductivityDetails teamProductivityDetails) {
        return teamProductivityService.createTeamProductivity(teamProductivityDetails);
    }

    @Override
    @PutMapping("/{id}")
    public TeamProductivityDto updateTeamProductivity(@PathVariable Long id,
                                                      @RequestBody @Valid TeamProductivityDetails teamProductivityDetails) {
        return teamProductivityService.updateTeamProductivity(id, teamProductivityDetails);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteTeamProductivity(@PathVariable Long id) {
        teamProductivityService.deleteTeamProductivity(id);
    }

}
