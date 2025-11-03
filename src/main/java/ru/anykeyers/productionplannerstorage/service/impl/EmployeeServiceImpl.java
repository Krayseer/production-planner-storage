package ru.anykeyers.productionplannerstorage.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.anykeyers.productionplannerstorage.domain.dto.EmployeeDto;
import ru.anykeyers.productionplannerstorage.domain.mapper.EmployeeMapper;
import ru.anykeyers.productionplannerstorage.domain.request.EmployeeDetails;
import ru.anykeyers.productionplannerstorage.domain.Employee;
import ru.anykeyers.productionplannerstorage.domain.Team;
import ru.anykeyers.productionplannerstorage.exception.EmployeeNotFoundException;
import ru.anykeyers.productionplannerstorage.exception.TeamNotFoundException;
import ru.anykeyers.productionplannerstorage.repository.EmployeeRepository;
import ru.anykeyers.productionplannerstorage.repository.TeamRepository;
import ru.anykeyers.productionplannerstorage.service.EmployeeService;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeMapper employeeMapper;
    private final TeamRepository teamRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeDto> getAllEmployees() {
        return employeeMapper.toDto(employeeRepository.findAll());
    }

    @Override
    public List<EmployeeDto> getEmployeesByTeamId(Long teamId) {
        return employeeMapper.toDto(employeeRepository.findAllByTeamId(teamId));
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDetails employeeDetails) {
        Team team = getTeam(employeeDetails.teamId());
        Employee employee = Employee.builder()
                .fullName(employeeDetails.fullName())
                .team(team)
                .position(employeeDetails.position())
                .qualification(employeeDetails.qualification())
                .active(employeeDetails.active())
                .build();
        Employee savedEmployee = employeeRepository.save(employee);
        log.info("Saved employee: {} ", savedEmployee);
        return employeeMapper.toDto(savedEmployee);
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDetails employeeDetails) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeNotFoundException(employeeId));
        Team team = getTeam(employeeDetails.teamId());
        employee.setFullName(employeeDetails.fullName());
        employee.setTeam(team);
        employee.setPosition(employeeDetails.position());
        employee.setQualification(employeeDetails.qualification());
        employee.setActive(employeeDetails.active());
        Employee updatedEmployee = employeeRepository.save(employee);
        log.info("Updated employee: {} ", updatedEmployee);
        return employeeMapper.toDto(updatedEmployee);
    }

    private Team getTeam(Long teamId) {
        return teamRepository.findById(teamId).orElseThrow(() -> new TeamNotFoundException(teamId));
    }

}
