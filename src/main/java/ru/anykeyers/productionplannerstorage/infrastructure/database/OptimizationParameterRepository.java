package ru.anykeyers.productionplannerstorage.infrastructure.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.anykeyers.productionplannerstorage.domain.model.OptimizationParameter;

import java.util.List;

/**
 * DAO параметров производительности
 */
@Repository
public interface OptimizationParameterRepository extends JpaRepository<OptimizationParameter, Long> {

    /**
     * @return список всех активных параметров
     */
    List<OptimizationParameter> findAllByActiveIsTrue();

}
