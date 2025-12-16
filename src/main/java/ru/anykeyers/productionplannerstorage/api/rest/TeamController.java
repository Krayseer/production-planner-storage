package ru.anykeyers.productionplannerstorage.api.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.anykeyers.productionplannerstorage.api.TeamApi;
import ru.anykeyers.productionplannerstorage.domain.team.TeamDto;
import ru.anykeyers.productionplannerstorage.domain.team.TeamDetails;
import ru.anykeyers.productionplannerstorage.domain.team.TeamService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(RestControllerPath.TEAMS)
class TeamController implements TeamApi {

    private final TeamService teamService;

    @Override
    @GetMapping
    public List<TeamDto> getTeams() {
        return teamService.getTeams();
    }

    @Override
    @GetMapping("/{id}")
    public TeamDto getTeam(@PathVariable Long id) {
        return teamService.getTeam(id);
    }

    @Override
    @PostMapping
    public TeamDto createTeam(@RequestBody @Valid TeamDetails teamDetails) {
        return teamService.createTeam(teamDetails);
    }

    @Override
    @PutMapping("/{id}")
    public TeamDto updateTeam(@PathVariable Long id, @RequestBody @Valid TeamDetails teamDetails) {
        return teamService.updateTeam(id, teamDetails);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
    }

}
