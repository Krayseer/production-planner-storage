package ru.anykeyers.productionplannerstorage.infrastructure.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.anykeyers.productionplannerstorage.domain.model.Team;

/**
 * DAO бригад
 */
@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}
