package ru.anykeyers.productionplannerstorage.domain.optimization.result;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptimizationResultRepository extends JpaRepository<OptimizationResult, Long> {
}
