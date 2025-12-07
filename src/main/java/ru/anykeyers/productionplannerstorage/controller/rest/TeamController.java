package ru.anykeyers.productionplannerstorage.controller.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.anykeyers.productionplannerstorage.controller.TeamApi;
import ru.anykeyers.productionplannerstorage.domain.dto.TeamDto;
import ru.anykeyers.productionplannerstorage.domain.request.TeamDetails;
import ru.anykeyers.productionplannerstorage.service.TeamService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(RestControllerPath.TEAMS)
public class TeamController implements TeamApi {

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
    @ResponseStatus(HttpStatus.CREATED)
    public TeamDto createTeam(@RequestBody @Valid TeamDetails teamDetails) {
        return teamService.createTeam(teamDetails);
    }

    @Override
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TeamDto updateTeam(@PathVariable Long id, @RequestBody @Valid TeamDetails teamDetails) {
        return teamService.updateTeam(id, teamDetails);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
    }

}
