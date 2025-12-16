package ru.anykeyers.productionplannerstorage.domain.optimization.run;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DAO параметров производительности
 */
@Repository
public interface OptimizationRunRepository extends JpaRepository<OptimizationRun, Long> {

}
