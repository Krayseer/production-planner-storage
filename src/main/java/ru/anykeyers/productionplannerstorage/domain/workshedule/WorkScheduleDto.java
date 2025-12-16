package ru.anykeyers.productionplannerstorage.domain.workshedule;

import ru.anykeyers.productionplannerstorage.domain.absencetype.AbsenceTypeDto;
import ru.anykeyers.productionplannerstorage.domain.employee.EmployeeDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record WorkScheduleDto(
        Long id,
        EmployeeDto employee,
        LocalDate workDate,
        BigDecimal hoursPlanned,
        BigDecimal hoursActual,
        AbsenceTypeDto absenceType,
        LocalDateTime createdAt
) {}
