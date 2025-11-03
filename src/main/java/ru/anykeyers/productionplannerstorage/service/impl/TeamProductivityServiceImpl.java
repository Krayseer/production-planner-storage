package ru.anykeyers.productionplannerstorage.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.anykeyers.productionplannerstorage.domain.dto.TeamProductivityDto;
import ru.anykeyers.productionplannerstorage.domain.mapper.TeamProductivityMapper;
import ru.anykeyers.productionplannerstorage.domain.request.TeamProductivityDetails;
import ru.anykeyers.productionplannerstorage.domain.Product;
import ru.anykeyers.productionplannerstorage.domain.Team;
import ru.anykeyers.productionplannerstorage.domain.TeamProductivity;
import ru.anykeyers.productionplannerstorage.domain.enums.ProductionType;
import ru.anykeyers.productionplannerstorage.exception.ProductNotFoundException;
import ru.anykeyers.productionplannerstorage.exception.TeamNotFoundException;
import ru.anykeyers.productionplannerstorage.exception.TeamProductivityNotFoundException;
import ru.anykeyers.productionplannerstorage.exception.TeamProductivityNotUniqueException;
import ru.anykeyers.productionplannerstorage.repository.ProductRepository;
import ru.anykeyers.productionplannerstorage.repository.TeamProductivityRepository;
import ru.anykeyers.productionplannerstorage.repository.TeamRepository;
import ru.anykeyers.productionplannerstorage.service.TeamProductivityService;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TeamProductivityServiceImpl implements TeamProductivityService {

    private final TeamRepository teamRepository;
    private final ProductRepository productRepository;
    private final TeamProductivityMapper teamProductivityMapper;
    private final TeamProductivityRepository teamProductivityRepository;

    @Override
    public List<TeamProductivityDto> getAllTeamProductivity() {
        return teamProductivityMapper.toDto(teamProductivityRepository.findAll());
    }

    @Override
    public List<TeamProductivityDto> getTeamProductivityByTeamId(Long teamId) {
        return teamProductivityMapper.toDto(teamProductivityRepository.findAllByTeamId(teamId));
    }

    @Override
    public List<TeamProductivityDto> getTeamProductivityByProductId(Long productId) {
        return teamProductivityMapper.toDto(teamProductivityRepository.findAllByProductId(productId));
    }

    @Override
    public TeamProductivityDto createTeamProductivity(TeamProductivityDetails teamProductivityDetails) {
        if (teamProductivityRepository.existsByTeamIdAndProductIdAndProductionType(teamProductivityDetails.teamId(),
                teamProductivityDetails.productId(), teamProductivityDetails.productionType())) {
            throw new TeamProductivityNotUniqueException(teamProductivityDetails.teamId(),
                    teamProductivityDetails.productId(), teamProductivityDetails.productionType());
        }
        Team team = getTeam(teamProductivityDetails.teamId());
        Product product = getProduct(teamProductivityDetails.productId());
        TeamProductivity teamProductivity = TeamProductivity.builder()
                .team(team)
                .product(product)
                .productionType(ProductionType.valueOf(teamProductivityDetails.productionType().toUpperCase()))
                .qualification(teamProductivityDetails.qualification())
                .productivity(teamProductivityDetails.productivity())
                .build();
        TeamProductivity savedTeamProductivity = teamProductivityRepository.save(teamProductivity);
        log.info("Create team productivity: {}", savedTeamProductivity);
        return teamProductivityMapper.toDto(savedTeamProductivity);
    }

    @Override
    public TeamProductivityDto updateTeamProductivity(Long teamProductivityId, TeamProductivityDetails teamProductivityDetails) {
        TeamProductivity teamProductivity = teamProductivityRepository.findById(teamProductivityId)
                .orElseThrow(() -> new TeamProductivityNotFoundException(teamProductivityId));
        if (teamProductivityRepository.existsByTeamIdAndProductIdAndProductionTypeAndIdNot(teamProductivityDetails.teamId(),
                teamProductivityDetails.productId(), teamProductivityDetails.productionType(), teamProductivityId)) {
            throw new TeamProductivityNotUniqueException(teamProductivityDetails.teamId(),
                    teamProductivityDetails.productId(), teamProductivityDetails.productionType());
        }
        Team team = getTeam(teamProductivityDetails.teamId());
        Product product = getProduct(teamProductivityDetails.productId());
        teamProductivity.setTeam(team);
        teamProductivity.setProduct(product);
        teamProductivity.setProductionType(ProductionType.valueOf(teamProductivityDetails.productionType().toUpperCase()));
        teamProductivity.setQualification(teamProductivityDetails.qualification());
        teamProductivity.setProductivity(teamProductivityDetails.productivity());
        TeamProductivity updatedTeamProductivity = teamProductivityRepository.save(teamProductivity);
        log.info("Updated team productivity: {}", updatedTeamProductivity);
        return teamProductivityMapper.toDto(updatedTeamProductivity);
    }

    @Override
    public void deleteTeamProductivity(Long teamProductivityId) {
        teamProductivityRepository.deleteById(teamProductivityId);
    }

    private Team getTeam(Long teamId) {
        return teamRepository.findById(teamId).orElseThrow(() -> new TeamNotFoundException(teamId));
    }

    private Product getProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
    }

}
