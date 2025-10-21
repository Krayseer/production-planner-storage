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
import ru.anykeyers.productionplannerstorage.controller.dto.ProductDto;
import ru.anykeyers.productionplannerstorage.controller.request.ProductDetails;

import java.util.List;

/**
 * Контракт для операций с изделиями
 */
@Tag(name = "Products", description = "API для управления изделиями")
@RequestMapping(ControllerPath.PRODUCTS)
public interface ProductApi {

    @Operation(
            summary = "Получить список всех изделий",
            description = "Возвращает список всех зарегистрированных изделий",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Успешное получение списка изделий",
                            content = @Content(schema = @Schema(implementation = ProductDto.class))
                    )
            }
    )
    @GetMapping
    List<ProductDto> getAllProducts();

    @Operation(
            summary = "Получить изделие по ID",
            description = "Возвращает изделие по указанному идентификатору",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Изделие найдено"),
                    @ApiResponse(responseCode = "404", description = "Изделие не найдено")
            }
    )
    @GetMapping("/{id}")
    ProductDto getProductById(
            @Parameter(description = "Уникальный идентификатор изделия", example = "1")
            @PathVariable long id
    );

    @Operation(
            summary = "Создать новое изделие",
            description = "Создает новую запись изделия и возвращает созданный объект",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Изделие успешно создано"),
                    @ApiResponse(responseCode = "400", description = "Ошибка валидации входных данных")
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ProductDto createProduct(
            @RequestBody(
                    required = true,
                    description = "Данные нового изделия",
                    content = @Content(schema = @Schema(implementation = ProductDetails.class))
            )
            @org.springframework.web.bind.annotation.RequestBody @Valid ProductDetails productDetails
    );

    @Operation(
            summary = "Обновить изделие по ID",
            description = "Обновляет существующее изделие по идентификатору",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Изделие успешно обновлено"),
                    @ApiResponse(responseCode = "404", description = "Изделие не найдено")
            }
    )
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ProductDto updateProduct(
            @Parameter(description = "ID изделия", example = "1") @PathVariable long id,
            @org.springframework.web.bind.annotation.RequestBody ProductDetails productDetails
    );

    @Operation(
            summary = "Удалить изделие по ID",
            description = "Удаляет изделие из системы",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Изделие успешно удалено"),
                    @ApiResponse(responseCode = "404", description = "Изделие не найдено")
            }
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteProduct(
            @Parameter(description = "ID изделия", example = "1") @PathVariable long id
    );

}
