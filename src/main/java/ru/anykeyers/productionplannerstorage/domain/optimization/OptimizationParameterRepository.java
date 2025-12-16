package ru.anykeyers.productionplannerstorage.domain.optimization;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DAO параметров производительности
 */
@Repository
public interface OptimizationParameterRepository extends JpaRepository<OptimizationRun, Long> {

    /**
     * @return список всех активных параметров
     */
    List<OptimizationRun> findAllByActiveIsTrue();

}
