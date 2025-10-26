package ru.anykeyers.productionplannerstorage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anykeyers.productionplannerstorage.domain.ProductionPlan;

/**
 * DAO планов производства
 */
public interface ProductionPlanRepository extends JpaRepository<ProductionPlan, Long> {
}
