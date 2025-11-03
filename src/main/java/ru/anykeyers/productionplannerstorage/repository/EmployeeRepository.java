package ru.anykeyers.productionplannerstorage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anykeyers.productionplannerstorage.domain.Employee;

import java.util.List;

/**
 * DAO сотрудников
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    /**
     * Получить список сотрудников бригады
     *
     * @param teamId идентификатор бригады
     */
    List<Employee> findAllByTeamId(Long teamId);

}
