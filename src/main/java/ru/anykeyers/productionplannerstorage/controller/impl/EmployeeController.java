package ru.anykeyers.productionplannerstorage.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.anykeyers.productionplannerstorage.controller.EmployeeApi;
import ru.anykeyers.productionplannerstorage.controller.dto.EmployeeDto;
import ru.anykeyers.productionplannerstorage.controller.request.EmployeeDetails;
import ru.anykeyers.productionplannerstorage.service.EmployeeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmployeeController implements EmployeeApi {

    private final EmployeeService employeeService;

    @Override
    public List<EmployeeDto> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @Override
    public List<EmployeeDto> getEmployeesByTeamId(Long teamId) {
        return employeeService.getEmployeesByTeamId(teamId);
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDetails employeeDetails) {
        return employeeService.createEmployee(employeeDetails);
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDetails employeeDetails) {
        return employeeService.updateEmployee(id, employeeDetails);
    }

}
