package ru.anykeyers.productionplannerstorage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anykeyers.productionplannerstorage.domain.Team;

/**
 * DAO бригад
 */
public interface TeamRepository extends JpaRepository<Team, Long> {
}
