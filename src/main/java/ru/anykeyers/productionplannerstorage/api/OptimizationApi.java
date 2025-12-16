package ru.anykeyers.productionplannerstorage.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import ru.anykeyers.productionplannerstorage.domain.optimization.run.OptimizationRunDto;
import ru.anykeyers.productionplannerstorage.domain.optimization.run.OptimizationRunDetails;

import java.util.List;

/**
 * Контракт для операций с оптимизацией
 */
@Tag(name = "Optimization Parameters", description = "API для управления параметрами оптимизации")
public interface OptimizationApi {

    @Operation(
            summary = "Получить список активных параметров оптимизации",
            description = "Возвращает список всех активных параметров оптимизации, используемых в модели",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список параметров успешно получен",
                            content = @Content(schema = @Schema(implementation = OptimizationRunDto.class))
                    )
            }
    )
    List<OptimizationRunDto> getActiveOptimizationRuns();

    @Operation(
            summary = "Создать новый параметр оптимизации",
            description = "Создаёт новую запись параметра оптимизации в системе",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Параметр успешно создан"),
                    @ApiResponse(responseCode = "400", description = "Ошибка валидации входных данных")
            }
    )
    OptimizationRunDto createOptimizationRun(
            @RequestBody(
                    required = true,
                    description = "Данные нового параметра оптимизации",
                    content = @Content(schema = @Schema(implementation = OptimizationRunDetails.class))
            )
            OptimizationRunDetails optimizationRunDetails
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
    OptimizationRunDto updateOptimizationRun(
            @Parameter(description = "Уникальный идентификатор параметра оптимизации", example = "1")
            Long id,
            OptimizationRunDetails optimizationRunDetails
    );

}
