package ru.anykeyers.productionplannerstorage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anykeyers.productionplannerstorage.domain.ProductionSession;

/**
 * DAO производственных сессий
 */
public interface ProductionSessionRepository extends JpaRepository<ProductionSession, Long> {
}
