package ru.anykeyers.productionplannerstorage.domain.employee;

import org.springframework.http.HttpStatus;
import ru.anykeyers.productionplannerstorage.exception.PlannerStorageResponseStatusException;

/**
 * Ошибка отсутствия сотрудника
 */
public class EmployeeNotFoundException extends PlannerStorageResponseStatusException {

    public EmployeeNotFoundException(Long employeeId) {
        super(HttpStatus.NOT_FOUND, "Employee not found with id {0}", employeeId);
    }

}