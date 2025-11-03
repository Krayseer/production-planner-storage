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
import ru.anykeyers.productionplannerstorage.domain.dto.ProductionPlanDto;
import ru.anykeyers.productionplannerstorage.domain.request.ProductionPlanDetails;

import java.util.List;

/**
 * Контракт для операций с планами производства
 */
@Tag(name = "ProductionPlans", description = "API для управления планами производства")
@RequestMapping(ControllerPath.PRODUCTION_PLANS)
public interface ProductionPlanApi {

    @Operation(
            summary = "Получить список всех планов производства",
            description = "Возвращает список всех зарегистрированных планов производства",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Успешное получение списка планов производства",
                            content = @Content(schema = @Schema(implementation = ProductionPlanDto.class))
                    )
            }
    )
    @GetMapping
    List<ProductionPlanDto> getAllProductionPlans();

    @Operation(
            summary = "Получить план производства по ID",
            description = "Возвращает план производства по указанному идентификатору",
            responses = {
                    @ApiResponse(responseCode = "200", description = "План производства найден"),
                    @ApiResponse(responseCode = "404", description = "План производства не найден")
            }
    )
    @GetMapping("/{id}")
    ProductionPlanDto getProductionPlan(
            @Parameter(description = "Уникальный идентификатор плана производства", example = "1")
            @PathVariable Long id
    );

    @Operation(
            summary = "Создать новый план производства",
            description = "Создает новую запись плана производства и возвращает созданный объект",
            responses = {
                    @ApiResponse(responseCode = "201", description = "План производства успешно создан"),
                    @ApiResponse(responseCode = "400", description = "Ошибка валидации входных данных")
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ProductionPlanDto createProductionPlan(
            @RequestBody(
                    required = true,
                    description = "Данные нового плана производства",
                    content = @Content(schema = @Schema(implementation = ProductionPlanDetails.class))
            )
            @org.springframework.web.bind.annotation.RequestBody @Valid ProductionPlanDetails productionPlanDetails
    );

    @Operation(
            summary = "Обновить план производства по ID",
            description = "Обновляет существующий план производства по идентификатору",
            responses = {
                    @ApiResponse(responseCode = "204", description = "План производства успешно обновлен"),
                    @ApiResponse(responseCode = "404", description = "План производства не найден")
            }
    )
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ProductionPlanDto updateProductionPlan(
            @Parameter(description = "ID плана производства", example = "1") @PathVariable Long id,
            @RequestBody(
                    required = true,
                    description = "Обновленные данные плана производства",
                    content = @Content(schema = @Schema(implementation = ProductionPlanDetails.class))
            )
            @org.springframework.web.bind.annotation.RequestBody ProductionPlanDetails productionPlanDetails
    );

    @Operation(
            summary = "Удалить план производства по ID",
            description = "Удаляет план производства из системы",
            responses = {
                    @ApiResponse(responseCode = "204", description = "План производства успешно удален"),
                    @ApiResponse(responseCode = "404", description = "План производства не найден")
            }
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteProductionPlan(
            @Parameter(description = "ID плана производства", example = "1") @PathVariable Long id
    );

}

