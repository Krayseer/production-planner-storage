package ru.anykeyers.productionplannerstorage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anykeyers.productionplannerstorage.domain.AssemblySchedule;

/**
 * DAO графиков слесарного участка
 */
public interface AssemblyScheduleRepository extends JpaRepository<AssemblySchedule, Long> {
}
