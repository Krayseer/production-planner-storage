package ru.anykeyers.productionplannerstorage.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.anykeyers.productionplannerstorage.domain.dto.WorkScheduleDto;
import ru.anykeyers.productionplannerstorage.domain.request.WorkScheduleDetails;

import java.util.List;

/**
 * Контракт для операций с табелем рабочего времени
 */
@Tag(name = "Work Schedules", description = "API для управления табелем рабочего времени сотрудников")
@RequestMapping(ControllerPath.WORK_SCHEDULES)
public interface WorkScheduleApi {

    @Operation(
            summary = "Получить список записей табеля рабочего времени",
            description = "Возвращает список всех записей табеля рабочего времени",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список записей успешно получен",
                            content = @Content(schema = @Schema(implementation = WorkScheduleDto.class))
                    )
            }
    )
    @GetMapping
    List<WorkScheduleDto> getWorkSchedules();

    @Operation(
            summary = "Создать запись табеля рабочего времени",
            description = "Создаёт новую запись табеля рабочего времени для сотрудника",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Запись успешно создана"),
                    @ApiResponse(responseCode = "400", description = "Ошибка валидации или нарушение уникальности"),
                    @ApiResponse(responseCode = "404", description = "Сотрудник или тип отсутствия не найден")
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    WorkScheduleDto createWorkSchedule(
            @RequestBody(
                    required = true,
                    description = "Данные новой записи табеля рабочего времени",
                    content = @Content(schema = @Schema(implementation = WorkScheduleDetails.class))
            )
            @org.springframework.web.bind.annotation.RequestBody @Valid WorkScheduleDetails workScheduleDetails
    );

    @Operation(
            summary = "Обновить запись табеля рабочего времени по ID",
            description = "Обновляет существующую запись табеля рабочего времени по её идентификатору",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Запись успешно обновлена"),
                    @ApiResponse(responseCode = "400", description = "Ошибка валидации или нарушение уникальности"),
                    @ApiResponse(responseCode = "404", description = "Запись табеля не найдена")
            }
    )
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    WorkScheduleDto updateWorkSchedule(
            @Parameter(description = "Уникальный идентификатор записи табеля рабочего времени", example = "1")
            @PathVariable Long id,
            @org.springframework.web.bind.annotation.RequestBody @Valid WorkScheduleDetails workScheduleDetails
    );

}
