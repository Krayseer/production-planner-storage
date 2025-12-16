package ru.anykeyers.productionplannerstorage.domain.session;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DAO производственных сессий
 */
@Repository
public interface ProductionSessionRepository extends JpaRepository<ProductionSession, Long> {
}
