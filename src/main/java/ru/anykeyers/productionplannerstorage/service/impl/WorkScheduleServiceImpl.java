package ru.anykeyers.productionplannerstorage.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.anykeyers.productionplannerstorage.domain.AbsenceType;
import ru.anykeyers.productionplannerstorage.domain.Employee;
import ru.anykeyers.productionplannerstorage.domain.WorkSchedule;
import ru.anykeyers.productionplannerstorage.domain.dto.WorkScheduleDto;
import ru.anykeyers.productionplannerstorage.domain.mapper.WorkScheduleMapper;
import ru.anykeyers.productionplannerstorage.domain.request.WorkScheduleDetails;
import ru.anykeyers.productionplannerstorage.exception.AbsenceTypeNotFoundException;
import ru.anykeyers.productionplannerstorage.exception.EmployeeNotFoundException;
import ru.anykeyers.productionplannerstorage.exception.WorkScheduleNotFoundException;
import ru.anykeyers.productionplannerstorage.exception.WorkScheduleNotUniqueException;
import ru.anykeyers.productionplannerstorage.repository.AbsenceTypeRepository;
import ru.anykeyers.productionplannerstorage.repository.EmployeeRepository;
import ru.anykeyers.productionplannerstorage.repository.WorkScheduleRepository;
import ru.anykeyers.productionplannerstorage.service.WorkScheduleService;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class WorkScheduleServiceImpl implements WorkScheduleService {

    private final WorkScheduleMapper workScheduleMapper;
    private final EmployeeRepository employeeRepository;
    private final AbsenceTypeRepository absenceTypeRepository;
    private final WorkScheduleRepository workScheduleRepository;

    @Override
    public List<WorkScheduleDto> getWorkSchedules() {
        return workScheduleMapper.toDto(workScheduleRepository.findAll());
    }

    @Override
    public WorkScheduleDto createWorkSchedule(WorkScheduleDetails workScheduleDetails) {
        checkUniqueWorkSchedule(workScheduleDetails);
        Employee employee = getEmployee(workScheduleDetails.employeeId());
        AbsenceType absenceType = getAbsenceType(workScheduleDetails.absenceTypeId());
        WorkSchedule workSchedule = WorkSchedule.builder()
                .employee(employee)
                .workDate(workScheduleDetails.workDate())
                .hoursPlanned(workScheduleDetails.hoursPlanned())
                .hoursActual(workScheduleDetails.hoursActual())
                .absenceType(absenceType)
                .build();
        WorkSchedule savedWorkSchedule = workScheduleRepository.save(workSchedule);
        log.info("Saved work schedule: {}", savedWorkSchedule);
        return workScheduleMapper.toDto(savedWorkSchedule);
    }

    @Override
    public WorkScheduleDto updateWorkSchedule(Long workScheduleId, WorkScheduleDetails workScheduleDetails) {
        WorkSchedule workSchedule = workScheduleRepository.findById(workScheduleId)
                .orElseThrow(() -> new WorkScheduleNotFoundException(workScheduleId));
        checkUniqueWorkSchedule(workScheduleDetails);
        Employee employee = getEmployee(workScheduleDetails.employeeId());
        AbsenceType absenceType = getAbsenceType(workScheduleDetails.absenceTypeId());
        workSchedule.setEmployee(employee);
        workSchedule.setWorkDate(workScheduleDetails.workDate());
        workSchedule.setHoursPlanned(workScheduleDetails.hoursPlanned());
        workSchedule.setHoursActual(workScheduleDetails.hoursActual());
        workSchedule.setAbsenceType(absenceType);
        WorkSchedule updatedWorkSchedule = workScheduleRepository.save(workSchedule);
        log.info("Updated work schedule: {}", updatedWorkSchedule);
        return workScheduleMapper.toDto(updatedWorkSchedule);
    }

    private void checkUniqueWorkSchedule(WorkScheduleDetails details) {
        if (workScheduleRepository.existsByEmployeeIdAndWorkDate(details.employeeId(), details.workDate())) {
            throw new WorkScheduleNotUniqueException(details.employeeId(), details.workDate());
        }
    }

    private Employee getEmployee(Long employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeNotFoundException(employeeId));
    }

    private AbsenceType getAbsenceType(Long absenceTypeId) {
        return absenceTypeRepository.findById(absenceTypeId)
                .orElseThrow(() -> new AbsenceTypeNotFoundException(absenceTypeId));
    }

}
