package ru.anykeyers.productionplannerstorage.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.anykeyers.productionplannerstorage.controller.dto.AbsenceTypeDto;
import ru.anykeyers.productionplannerstorage.controller.request.AbsenceTypeDetails;

import java.util.List;

/**
 * Контракт для операций с типами отсутствий
 */
@Tag(name = "Absence Types", description = "API для управления типами отсутствий сотрудников")
@RequestMapping(ControllerPath.ABSENCE_TYPES)
public interface AbsenceTypeApi {

    @Operation(
            summary = "Получить список типов отсутствий",
            description = "Возвращает все существующие типы отсутствий, включая активные и неактивные",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список типов отсутствий успешно получен",
                            content = @Content(schema = @Schema(implementation = AbsenceTypeDto.class))
                    )
            }
    )
    @GetMapping
    List<AbsenceTypeDto> getAbsenceTypes();

    @Operation(
            summary = "Получить детали типа отсутствия",
            description = "Возвращает детальную информацию о типе отсутствия по идентификатору",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Тип отсутствия успешно найден"),
                    @ApiResponse(responseCode = "404", description = "Тип отсутствия не найден")
            }
    )
    @GetMapping("/{id}")
    AbsenceTypeDto getAbsenceTypeById(
            @Parameter(description = "Уникальный идентификатор типа отсутствия", example = "1")
            @PathVariable("id") Long absenceTypeId
    );

    @Operation(
            summary = "Создать новый тип отсутствия",
            description = "Создаёт новую запись типа отсутствия в системе",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Тип отсутствия успешно создан"),
                    @ApiResponse(responseCode = "400", description = "Ошибка валидации входных данных или нарушение уникальности кода")
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    AbsenceTypeDto createAbsenceType(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Данные нового типа отсутствия",
                    content = @Content(schema = @Schema(implementation = AbsenceTypeDetails.class))
            )
            @Valid @RequestBody AbsenceTypeDetails absenceTypeDetails
    );

    @Operation(
            summary = "Обновить тип отсутствия по ID",
            description = "Обновляет существующий тип отсутствия по его идентификатору",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Тип отсутствия успешно обновлён"),
                    @ApiResponse(responseCode = "404", description = "Тип отсутствия не найден"),
                    @ApiResponse(responseCode = "400", description = "Ошибка валидации входных данных или нарушение уникальности кода")
            }
    )
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    AbsenceTypeDto updateAbsenceType(
            @Parameter(description = "Уникальный идентификатор типа отсутствия", example = "1")
            @PathVariable("id") Long absenceTypeId,
            @Valid @RequestBody AbsenceTypeDetails absenceTypeDetails
    );

}
