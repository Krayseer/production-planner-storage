package ru.anykeyers.productionplannerstorage.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.anykeyers.productionplannerstorage.domain.DtoMapper;
import ru.anykeyers.productionplannerstorage.domain.model.AbsenceType;
import ru.anykeyers.productionplannerstorage.domain.model.Employee;
import ru.anykeyers.productionplannerstorage.domain.model.WorkSchedule;
import ru.anykeyers.productionplannerstorage.domain.dto.WorkScheduleDto;
import ru.anykeyers.productionplannerstorage.domain.dto.request.WorkScheduleDetails;
import ru.anykeyers.productionplannerstorage.exception.AbsenceTypeNotFoundException;
import ru.anykeyers.productionplannerstorage.exception.EmployeeNotFoundException;
import ru.anykeyers.productionplannerstorage.exception.WorkScheduleNotFoundException;
import ru.anykeyers.productionplannerstorage.exception.WorkScheduleNotUniqueException;
import ru.anykeyers.productionplannerstorage.infrastructure.database.AbsenceTypeRepository;
import ru.anykeyers.productionplannerstorage.infrastructure.database.EmployeeRepository;
import ru.anykeyers.productionplannerstorage.infrastructure.database.WorkScheduleRepository;

import java.util.List;

/**
 * Сервис управления табелем рабочего времени
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class WorkScheduleService {

    private final EmployeeRepository employeeRepository;
    private final AbsenceTypeRepository absenceTypeRepository;
    private final WorkScheduleRepository workScheduleRepository;
    private final DtoMapper<WorkSchedule, WorkScheduleDto> workScheduleMapper;

    /**
     * @return записи табеля
     */
    public List<WorkScheduleDto> getWorkSchedules() {
        return workScheduleMapper.toDto(workScheduleRepository.findAll());
    }

    /**
     * Создать запись табеля рабочего времени
     *
     * @param workScheduleDetails данные для создания табеля рабочего времени
     */
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

    /**
     * Обновить запись табеля рабочего времени
     *
     * @param workScheduleId        идентификатор записи табеля рабочего времени
     * @param workScheduleDetails   обновленные данные табеля рабочего времени
     */
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
