package ru.anykeyers.productionplannerstorage.service;

import ru.anykeyers.productionplannerstorage.controller.dto.EmployeeDto;
import ru.anykeyers.productionplannerstorage.controller.request.EmployeeDetails;
import ru.anykeyers.productionplannerstorage.exception.EmployeeNotFoundException;
import ru.anykeyers.productionplannerstorage.exception.TeamNotFoundException;

import java.util.List;

/**
 * Сервис сотрудников
 */
public interface EmployeeService {

    /**
     * @return список сотрудников
     */
    List<EmployeeDto> getAllEmployees();

    /**
     * Получить список сотрудников бригады
     *
     * @param teamId идентификатор бригады
     */
    List<EmployeeDto> getEmployeesByTeamId(Long teamId);

    /**
     * Создать нового сотрудника
     *
     * @param employeeDetails данные о новом сотруднике
     */
    EmployeeDto createEmployee(EmployeeDetails employeeDetails) throws TeamNotFoundException;

    /**
     * Обновить данные о сотруднике
     *
     * @param employeeId        идентификатор сотрудника
     * @param employeeDetails   обновленные данные о сотруднике
     */
    EmployeeDto updateEmployee(Long employeeId, EmployeeDetails employeeDetails)
            throws TeamNotFoundException, EmployeeNotFoundException;

}
