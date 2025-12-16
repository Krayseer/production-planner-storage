package ru.anykeyers.productionplannerstorage.domain.team.productivity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.anykeyers.productionplannerstorage.domain.DtoMapper;
import ru.anykeyers.productionplannerstorage.domain.team.Team;
import ru.anykeyers.productionplannerstorage.domain.team.TeamNotFoundException;
import ru.anykeyers.productionplannerstorage.domain.team.TeamRepository;
import ru.anykeyers.productionplannerstorage.domain.product.Product;
import ru.anykeyers.productionplannerstorage.domain.ProductionType;
import ru.anykeyers.productionplannerstorage.domain.product.ProductNotFoundException;
import ru.anykeyers.productionplannerstorage.domain.product.ProductRepository;

import java.util.List;

/**
 * Сервис матриц производительности бригад
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TeamProductivityService {

    private final TeamRepository teamRepository;
    private final ProductRepository productRepository;
    private final TeamProductivityRepository teamProductivityRepository;
    private final DtoMapper<TeamProductivity, TeamProductivityDto> teamProductivityMapper;

    /**
     * @return список записей производительности бригад
     */
    public List<TeamProductivityDto> getAllTeamProductivity() {
        return teamProductivityMapper.toDto(teamProductivityRepository.findAll());
    }

    /**
     * Получить записи производительности бригады
     *
     * @param teamId идентификатор бригады
     */
    public List<TeamProductivityDto> getTeamProductivityByTeamId(Long teamId) {
        return teamProductivityMapper.toDto(teamProductivityRepository.findAllByTeamId(teamId));
    }

    /**
     * Получить записи производительности бригады
     *
     * @param productId идентификатор продукта
     */
    public List<TeamProductivityDto> getTeamProductivityByProductId(Long productId) {
        return teamProductivityMapper.toDto(teamProductivityRepository.findAllByProductId(productId));
    }

    /**
     * Создать запись производительности бригады
     *
     * @param teamProductivityDetails данные о производительности бригады
     */
    public TeamProductivityDto createTeamProductivity(TeamProductivityDetails teamProductivityDetails) {
        if (teamProductivityRepository.existsByTeamIdAndProductIdAndProductionType(teamProductivityDetails.teamId(),
                teamProductivityDetails.productId(), toProductionType(teamProductivityDetails.productionType()))) {
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

    /**
     * Обновить запись производительности бригады
     *
     * @param teamProductivityId        идентификатор записи производительности бригады
     * @param teamProductivityDetails   обновленные данные о записи производительности бригады
     */
    public TeamProductivityDto updateTeamProductivity(Long teamProductivityId, TeamProductivityDetails teamProductivityDetails) {
        TeamProductivity teamProductivity = teamProductivityRepository.findById(teamProductivityId)
                .orElseThrow(() -> new TeamProductivityNotFoundException(teamProductivityId));
        if (teamProductivityRepository.existsByTeamIdAndProductIdAndProductionTypeAndIdNot(teamProductivityDetails.teamId(),
                teamProductivityDetails.productId(), toProductionType(teamProductivityDetails.productionType()), teamProductivityId)) {
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

    /**
     * Удалить запись производительности бригады
     *
     * @param teamProductivityId идентификатор записи производительности бригады
     */
    public void deleteTeamProductivity(Long teamProductivityId) {
        teamProductivityRepository.deleteById(teamProductivityId);
    }

    private Team getTeam(Long teamId) {
        return teamRepository.findById(teamId).orElseThrow(() -> new TeamNotFoundException(teamId));
    }

    private Product getProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
    }

    private ProductionType toProductionType(String productionType) {
        return ProductionType.valueOf(productionType.toUpperCase());
    }

}
