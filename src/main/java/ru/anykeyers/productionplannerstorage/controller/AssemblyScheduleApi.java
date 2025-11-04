package ru.anykeyers.productionplannerstorage.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.anykeyers.productionplannerstorage.domain.dto.AssemblyScheduleDto;
import ru.anykeyers.productionplannerstorage.domain.request.AssemblyScheduleDetails;

import jakarta.validation.Valid;
import java.util.List;

/**
 * Контракт для операций с графиками слесарных участков
 */
@Tag(name = "Assembly Schedule", description = "API для управления графиками слесарных участков")
@RequestMapping(ControllerPath.ASSEMBLY_SCHEDULES)
public interface AssemblyScheduleApi {

    @Operation(
            summary = "Получить список графиков слесарных участков",
            description = "Возвращает все записи графиков по слесарным участкам",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список графиков успешно получен",
                            content = @Content(schema = @Schema(implementation = AssemblyScheduleDto.class))
                    )
            }
    )
    @GetMapping
    List<AssemblyScheduleDto> getAssemblySchedules();

    @Operation(
            summary = "Создать график слесарного участка",
            description = "Создаёт новую запись графика работы слесарного участка",
            responses = {
                    @ApiResponse(responseCode = "201", description = "График успешно создан"),
                    @ApiResponse(responseCode = "400", description = "Ошибка валидации входных данных"),
                    @ApiResponse(responseCode = "404", description = "Бригада или изделие не найдены")
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    AssemblyScheduleDto createAssemblySchedule(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Данные для создания графика слесарного участка",
                    content = @Content(schema = @Schema(implementation = AssemblyScheduleDetails.class))
            )
            @RequestBody @Valid AssemblyScheduleDetails assemblyScheduleDetails
    );

    @Operation(
            summary = "Обновить график слесарного участка",
            description = "Обновляет существующую запись графика по её идентификатору",
            responses = {
                    @ApiResponse(responseCode = "200", description = "График успешно обновлён"),
                    @ApiResponse(responseCode = "400", description = "Ошибка валидации входных данных"),
                    @ApiResponse(responseCode = "404", description = "График, бригада или изделие не найдены")
            }
    )
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    AssemblyScheduleDto updateAssemblySchedule(
            @Parameter(description = "Уникальный идентификатор графика слесарного участка", example = "1")
            @PathVariable("id") Long assemblyScheduleId,
            @RequestBody @Valid AssemblyScheduleDetails assemblyScheduleDetails
    );

}
