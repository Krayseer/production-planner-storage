package ru.anykeyers.productionplannerstorage.domain.workshedule;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record WorkScheduleDetails(
        @NotNull(message = "employee_id не может быть null")
        Long employeeId,

        @NotNull(message = "work_date не может быть null")
        LocalDate workDate,

        @NotNull(message = "hours_planned не может быть null")
        @DecimalMin(value = "0.0", message = "hours_planned должно быть не меньше 0")
        @DecimalMax(value = "24.0", message = "hours_planned должно быть не больше 24")
        BigDecimal hoursPlanned,

        @NotNull(message = "hours_actual не может быть null")
        @DecimalMin(value = "0.0", message = "hours_actual должно быть не меньше 0")
        @DecimalMax(value = "24.0", message = "hours_actual должно быть не больше 24")
        BigDecimal hoursActual,

        @NotNull(message = "absence_type_id не может быть null")
        Long absenceTypeId
) {}
