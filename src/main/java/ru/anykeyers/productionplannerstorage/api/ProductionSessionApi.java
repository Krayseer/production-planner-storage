package ru.anykeyers.productionplannerstorage.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import ru.anykeyers.productionplannerstorage.domain.dto.ProductionSessionDto;
import ru.anykeyers.productionplannerstorage.domain.dto.request.ProductionSessionDetails;
import ru.anykeyers.productionplannerstorage.domain.dto.request.SessionOrderDetails;

import java.util.List;

/**
 * Контракт для операций с производственными сессиями
 */
@Tag(name = "Production sessions", description = "API для управления производственными сессиями")
public interface ProductionSessionApi {

    @Operation(
            summary = "Получить список всех сессий",
            description = "Возвращает список всех зарегистрированных сессий",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Успешное получение списка зарегистрированных сессий",
                            content = @Content(schema = @Schema(implementation = ProductionSessionDto.class))
                    )
            }
    )
    List<ProductionSessionDto> getAllProductionSessions();

    @Operation(
            summary = "Получить производственную сессию по ID",
            description = "Возвращает производственную сессию по указанному идентификатору",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Производственная сессия найдена"),
                    @ApiResponse(responseCode = "404", description = "Производственная сессия не найдена")
            }
    )
    ProductionSessionDto getProductionSession(
            @Parameter(description = "Уникальный идентификатор производственной сессии", example = "1") Long id);

    @Operation(
            summary = "Создать новую производственную сессию",
            description = "Создает новую производственную сессию и возвращает созданный объект",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Производственная сессия успешно создана"),
                    @ApiResponse(responseCode = "400", description = "Ошибка валидации входных данных")
            }
    )
    ProductionSessionDto createProductionSession(
            @RequestBody(
                    required = true,
                    description = "Данные новой производственной сессии",
                    content = @Content(schema = @Schema(implementation = ProductionSessionDetails.class))
            )
            ProductionSessionDetails productionSessionDetails
    );

    @Operation(
            summary = "Создать новый заказ в сессии",
            description = "Создает новый заказ в сессии и возвращает созданный объект",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Заказ в сессии успешно создан"),
                    @ApiResponse(responseCode = "400", description = "Ошибка валидации входных данных")
            }
    )
    ProductionSessionDto createProductionSessionOrder(
            @Parameter(description = "ID производственной сессии", example = "1") Long id,
            @RequestBody(
                    required = true,
                    description = "Данные о новом заказе в сессии",
                    content = @Content(schema = @Schema(implementation = SessionOrderDetails.class))
            )
            SessionOrderDetails sessionOrderDetails
    );

    @Operation(
            summary = "Обновить данные о производственной сессии по ID",
            description = "Обновляет существующую производственную сессию по идентификатору",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Производственная сессия успешно обновлена"),
                    @ApiResponse(responseCode = "404", description = "Производственная сессия не найдена")
            }
    )
    ProductionSessionDto updateProductionSession(
            @Parameter(description = "ID производственной сессии", example = "1") Long id,
            @RequestBody(
                    required = true,
                    description = "Обновленные данные производственной сессии",
                    content = @Content(schema = @Schema(implementation = ProductionSessionDetails.class))
            )
            ProductionSessionDetails productionSessionDetails
    );

    @Operation(
            summary = "Удалить производственную сессию по ID",
            description = "Удаляет производственную сессию из системы",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Производственная сессия успешно удалена"),
                    @ApiResponse(responseCode = "404", description = "Производственная сессия не найдена")
            }
    )
    void deleteProductionPlan(@Parameter(description = "ID плана производства", example = "1") Long id);

}

