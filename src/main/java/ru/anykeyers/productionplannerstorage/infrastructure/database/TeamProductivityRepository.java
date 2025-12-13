package ru.anykeyers.productionplannerstorage.infrastructure.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.anykeyers.productionplannerstorage.domain.model.TeamProductivity;
import ru.anykeyers.productionplannerstorage.domain.model.ProductionType;

import java.util.List;

/**
 * DAO записей производительности бригады
 */
@Repository
public interface TeamProductivityRepository extends JpaRepository<TeamProductivity, Long> {

    /**
     * Получить список всех записей производительности бригады
     *
     * @param teamId идентификатор бригады
     */
    List<TeamProductivity> findAllByTeamId(Long teamId);

    /**
     * Получить список всех записей производительности бригады
     *
     * @param productId идентификатор продукта
     */
    List<TeamProductivity> findAllByProductId(Long productId);

    /**
     * Существует ли запись производительности бригады с указанными идентификатором бригады, идентификатором продукта
     * и типом производства
     */
    boolean existsByTeamIdAndProductIdAndProductionType(Long teamId, Long productId, ProductionType productionType);

    /**
     * Аналог {@link #existsByTeamIdAndProductIdAndProductionType(Long, Long, ProductionType)}, но без учета записи с указанным
     * идентификатором {@code id}
     */
    boolean existsByTeamIdAndProductIdAndProductionTypeAndIdNot(Long teamId, Long productId, ProductionType productionType, Long id);

}
