package ru.anykeyers.productionplannerstorage.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import ru.anykeyers.productionplannerstorage.domain.team.productivity.TeamProductivityDto;
import ru.anykeyers.productionplannerstorage.domain.team.productivity.TeamProductivityDetails;

import java.util.List;

@Tag(name = "Team Productivity", description = "API для управления матрицами производительности бригад")
public interface TeamProductivityApi {

    @Operation(
            summary = "Получить все записи производительности бригад",
            description = "Возвращает список всех записей производительности бригад",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Список записей успешно получен",
                            content = @Content(schema = @Schema(implementation = TeamProductivityDto.class)))
            }
    )
    List<TeamProductivityDto> getAllTeamProductivity();

    @Operation(
            summary = "Получить записи производительности по ID бригады",
            description = "Возвращает все записи производительности для указанной бригады",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Записи успешно получены",
                            content = @Content(schema = @Schema(implementation = TeamProductivityDto.class))),
                    @ApiResponse(responseCode = "404", description = "Бригада не найдена")
            }
    )
    List<TeamProductivityDto> getTeamProductivityByTeamId(
            @Parameter(description = "ID бригады", example = "1") Long teamId);

    @Operation(
            summary = "Получить записи производительности по ID продукта",
            description = "Возвращает все записи производительности для указанного продукта",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Записи успешно получены",
                            content = @Content(schema = @Schema(implementation = TeamProductivityDto.class))),
                    @ApiResponse(responseCode = "404", description = "Продукт не найден")
            }
    )
    List<TeamProductivityDto> getTeamProductivityByProductId(
            @Parameter(description = "ID продукта", example = "1") Long productId);

    @Operation(
            summary = "Создать запись производительности бригады",
            description = "Создаёт новую запись производительности бригады",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Запись успешно создана"),
                    @ApiResponse(responseCode = "400", description = "Ошибка валидации или дубликат записи"),
                    @ApiResponse(responseCode = "404", description = "Бригада или продукт не найдены")
            }
    )
    TeamProductivityDto createTeamProductivity(
            @RequestBody(
                    required = true,
                    description = "Данные новой записи производительности",
                    content = @Content(schema = @Schema(implementation = TeamProductivityDetails.class))
            )
            TeamProductivityDetails teamProductivityDetails
    );

    @Operation(
            summary = "Обновить запись производительности бригады",
            description = "Обновляет существующую запись производительности по идентификатору",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Запись успешно обновлена"),
                    @ApiResponse(responseCode = "400", description = "Ошибка валидации или дубликат записи"),
                    @ApiResponse(responseCode = "404", description = "Запись, бригада или продукт не найдены")
            }
    )
    TeamProductivityDto updateTeamProductivity(
            @Parameter(description = "ID записи производительности", example = "1") Long id,
            TeamProductivityDetails teamProductivityDetails
    );

    @Operation(
            summary = "Удалить запись производительности бригады",
            description = "Удаляет запись производительности из системы",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Запись успешно удалена"),
                    @ApiResponse(responseCode = "404", description = "Запись не найдена")
            }
    )
    void deleteTeamProductivity(@Parameter(description = "ID записи производительности", example = "1") Long id);

}