package ru.anykeyers.productionplannerstorage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anykeyers.productionplannerstorage.domain.TeamProductivity;

import java.util.List;

/**
 * DAO записей производительности бригады
 */
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
    boolean existsByTeamIdAndProductIdAndProductionType(Long teamId, Long productId, String productionType);

    /**
     * Аналог {@link #existsByTeamIdAndProductIdAndProductionType(Long, Long, String)}, но без учета записи с указанным
     * идентификатором {@code id}
     */
    boolean existsByTeamIdAndProductIdAndProductionTypeAndIdNot(Long teamId, Long productId, String productionType, Long id);

}
