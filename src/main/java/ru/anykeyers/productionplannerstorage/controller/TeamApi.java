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
import ru.anykeyers.productionplannerstorage.controller.dto.TeamDto;
import ru.anykeyers.productionplannerstorage.controller.request.TeamDetails;

import java.util.List;

/**
 * Контракт для управления бригадами
 */
@Tag(name = "Teams", description = "API для управления бригадами")
@RequestMapping(ControllerPath.TEAMS)
public interface TeamApi {

    @Operation(
            summary = "Получить список всех бригад",
            description = "Возвращает список всех зарегистрированных бригад",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список бригад успешно получен",
                            content = @Content(schema = @Schema(implementation = TeamDto.class))
                    )
            }
    )
    @GetMapping
    List<TeamDto> getTeams();

    @Operation(
            summary = "Получить бригаду по ID",
            description = "Возвращает детали бригады по её уникальному идентификатору",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Бригада найдена"),
                    @ApiResponse(responseCode = "404", description = "Бригада не найдена")
            }
    )
    @GetMapping("/{id}")
    TeamDto getTeam(
            @Parameter(description = "Уникальный идентификатор бригады", example = "1")
            @PathVariable Long id
    );

    @Operation(
            summary = "Создать новую бригаду",
            description = "Создаёт новую запись бригады и возвращает созданный объект",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Бригада успешно создана"),
                    @ApiResponse(responseCode = "400", description = "Ошибка валидации входных данных")
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    TeamDto createTeam(
            @RequestBody(
                    required = true,
                    description = "Данные новой бригады",
                    content = @Content(schema = @Schema(implementation = TeamDetails.class))
            )
            @org.springframework.web.bind.annotation.RequestBody @Valid TeamDetails teamDetails
    );

    @Operation(
            summary = "Обновить бригаду по ID",
            description = "Обновляет существующую бригаду по указанному идентификатору",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Бригада успешно обновлена"),
                    @ApiResponse(responseCode = "404", description = "Бригада не найдена"),
                    @ApiResponse(responseCode = "400", description = "Ошибка валидации входных данных")
            }
    )
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    TeamDto updateTeam(
            @Parameter(description = "ID бригады", example = "1") @PathVariable Long id,
            @org.springframework.web.bind.annotation.RequestBody @Valid TeamDetails teamDetails
    );

    @Operation(
            summary = "Удалить бригаду по ID",
            description = "Удаляет бригаду из системы",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Бригада успешно удалена"),
                    @ApiResponse(responseCode = "404", description = "Бригада не найдена")
            }
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteTeam(
            @Parameter(description = "ID бригады", example = "1") @PathVariable Long id
    );

}
