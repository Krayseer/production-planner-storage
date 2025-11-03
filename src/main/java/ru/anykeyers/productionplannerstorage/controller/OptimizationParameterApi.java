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
import ru.anykeyers.productionplannerstorage.domain.dto.OptimizationParameterDto;
import ru.anykeyers.productionplannerstorage.domain.request.OptimizationParameterDetails;

import java.util.List;

/**
 * Контракт для операций с параметрами оптимизации
 */
@Tag(name = "Optimization Parameters", description = "API для управления параметрами оптимизации")
@RequestMapping(ControllerPath.OPTIMIZATION_PARAMETERS)
public interface OptimizationParameterApi {

    @Operation(
            summary = "Получить список активных параметров оптимизации",
            description = "Возвращает список всех активных параметров оптимизации, используемых в модели",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список параметров успешно получен",
                            content = @Content(schema = @Schema(implementation = OptimizationParameterDto.class))
                    )
            }
    )
    @GetMapping
    List<OptimizationParameterDto> getActiveOptimizationParameters();

    @Operation(
            summary = "Создать новый параметр оптимизации",
            description = "Создаёт новую запись параметра оптимизации в системе",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Параметр успешно создан"),
                    @ApiResponse(responseCode = "400", description = "Ошибка валидации входных данных")
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    OptimizationParameterDto createOptimizationParameter(
            @RequestBody(
                    required = true,
                    description = "Данные нового параметра оптимизации",
                    content = @Content(schema = @Schema(implementation = OptimizationParameterDetails.class))
            )
            @org.springframework.web.bind.annotation.RequestBody @Valid OptimizationParameterDetails optimizationParameterDetails
    );

    @Operation(
            summary = "Обновить параметр оптимизации по ID",
            description = "Обновляет существующий параметр оптимизации по его идентификатору",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Параметр успешно обновлён"),
                    @ApiResponse(responseCode = "404", description = "Параметр не найден"),
                    @ApiResponse(responseCode = "400", description = "Ошибка валидации входных данных")
            }
    )
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    OptimizationParameterDto updateOptimizationParameter(
            @Parameter(description = "Уникальный идентификатор параметра оптимизации", example = "1")
            @PathVariable Long id,
            @org.springframework.web.bind.annotation.RequestBody @Valid OptimizationParameterDetails optimizationParameterDetails
    );

}
