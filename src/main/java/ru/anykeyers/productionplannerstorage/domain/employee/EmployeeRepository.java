package ru.anykeyers.productionplannerstorage.domain.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DAO сотрудников
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    /**
     * Получить список сотрудников бригады
     *
     * @param teamId идентификатор бригады
     */
    List<Employee> findAllByTeamId(Long teamId);

}
