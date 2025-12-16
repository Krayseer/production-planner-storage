package ru.anykeyers.productionplannerstorage.domain.employee;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.anykeyers.productionplannerstorage.domain.DtoMapper;
import ru.anykeyers.productionplannerstorage.domain.team.Team;
import ru.anykeyers.productionplannerstorage.domain.team.TeamNotFoundException;
import ru.anykeyers.productionplannerstorage.domain.team.TeamRepository;

import java.util.List;

/**
 * Сервис сотрудников
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeService {

    private final TeamRepository teamRepository;
    private final EmployeeRepository employeeRepository;
    private final DtoMapper<Employee, EmployeeDto> employeeMapper;

    /**
     * @return список сотрудников
     */
    public List<EmployeeDto> getAllEmployees() {
        return employeeMapper.toDto(employeeRepository.findAll());
    }

    /**
     * Получить список сотрудников бригады
     *
     * @param teamId идентификатор бригады
     */
    public List<EmployeeDto> getEmployeesByTeamId(Long teamId) {
        return employeeMapper.toDto(employeeRepository.findAllByTeamId(teamId));
    }

    /**
     * Создать нового сотрудника
     *
     * @param employeeDetails данные о новом сотруднике
     */
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

    /**
     * Обновить данные о сотруднике
     *
     * @param employeeId        идентификатор сотрудника
     * @param employeeDetails   обновленные данные о сотруднике
     */
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
