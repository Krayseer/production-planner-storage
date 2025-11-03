package ru.anykeyers.productionplannerstorage.domain.dto;

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
