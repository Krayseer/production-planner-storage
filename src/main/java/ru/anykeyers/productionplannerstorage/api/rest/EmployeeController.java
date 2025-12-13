package ru.anykeyers.productionplannerstorage.api.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.anykeyers.productionplannerstorage.api.EmployeeApi;
import ru.anykeyers.productionplannerstorage.domain.dto.EmployeeDto;
import ru.anykeyers.productionplannerstorage.domain.dto.request.EmployeeDetails;
import ru.anykeyers.productionplannerstorage.domain.service.EmployeeService;

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
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDto createEmployee(@RequestBody @Valid EmployeeDetails employeeDetails) {
        return employeeService.createEmployee(employeeDetails);
    }

    @Override
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDto updateEmployee( @PathVariable Long id, @RequestBody @Valid EmployeeDetails employeeDetails) {
        return employeeService.updateEmployee(id, employeeDetails);
    }

}
