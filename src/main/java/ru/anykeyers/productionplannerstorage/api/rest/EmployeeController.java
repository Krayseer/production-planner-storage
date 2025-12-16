package ru.anykeyers.productionplannerstorage.api.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.anykeyers.productionplannerstorage.api.EmployeeApi;
import ru.anykeyers.productionplannerstorage.domain.employee.EmployeeDto;
import ru.anykeyers.productionplannerstorage.domain.employee.EmployeeDetails;
import ru.anykeyers.productionplannerstorage.domain.employee.EmployeeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(RestControllerPath.EMPLOYEES)
class EmployeeController implements EmployeeApi {

    private final EmployeeService employeeService;

    @Override
    @GetMapping
    public List<EmployeeDto> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @Override
    @GetMapping("/team/{teamId}")
    public List<EmployeeDto> getEmployeesByTeamId(@PathVariable Long teamId) {
        return employeeService.getEmployeesByTeamId(teamId);
    }

    @Override
    @PostMapping
    public EmployeeDto createEmployee(@RequestBody @Valid EmployeeDetails employeeDetails) {
        return employeeService.createEmployee(employeeDetails);
    }

    @Override
    @PutMapping("/{id}")
    public EmployeeDto updateEmployee( @PathVariable Long id, @RequestBody @Valid EmployeeDetails employeeDetails) {
        return employeeService.updateEmployee(id, employeeDetails);
    }

}
