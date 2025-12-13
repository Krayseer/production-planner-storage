package ru.anykeyers.productionplannerstorage.infrastructure.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.anykeyers.productionplannerstorage.domain.model.AssemblySchedule;

/**
 * DAO графиков слесарного участка
 */
@Repository
public interface AssemblyScheduleRepository extends JpaRepository<AssemblySchedule, Long> {
}
