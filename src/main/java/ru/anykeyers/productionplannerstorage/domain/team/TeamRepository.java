package ru.anykeyers.productionplannerstorage.domain.team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DAO бригад
 */
@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}
