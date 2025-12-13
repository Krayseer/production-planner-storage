package ru.anykeyers.productionplannerstorage.infrastructure.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.anykeyers.productionplannerstorage.domain.model.ProductionSession;

/**
 * DAO производственных сессий
 */
@Repository
public interface ProductionSessionRepository extends JpaRepository<ProductionSession, Long> {
}
