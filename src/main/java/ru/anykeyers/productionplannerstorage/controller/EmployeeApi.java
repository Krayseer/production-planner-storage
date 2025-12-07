package ru.anykeyers.productionplannerstorage.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import ru.anykeyers.productionplannerstorage.domain.dto.EmployeeDto;
import ru.anykeyers.productionplannerstorage.domain.request.EmployeeDetails;

import java.util.List;

@Tag(name = "Employees", description = "API для управления сотрудниками")
public interface EmployeeApi {

    @Operation(
            summary = "Получить всех сотрудников",
            description = "Возвращает список всех сотрудников системы",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Список сотрудников успешно получен",
                            content = @Content(schema = @Schema(implementation = EmployeeDto.class)))
            }
    )
    List<EmployeeDto> getAllEmployees();

    @Operation(
            summary = "Получить сотрудников бригады",
            description = "Возвращает список сотрудников, относящихся к указанной бригаде",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Список сотрудников успешно получен",
                            content = @Content(schema = @Schema(implementation = EmployeeDto.class))),
                    @ApiResponse(responseCode = "404", description = "Бригада не найдена")
            }
    )
    List<EmployeeDto> getEmployeesByTeamId(@Parameter(description = "ID бригады", example = "1") Long teamId);

    @Operation(
            summary = "Создать нового сотрудника",
            description = "Создаёт нового сотрудника и возвращает созданный объект",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Сотрудник успешно создан"),
                    @ApiResponse(responseCode = "400", description = "Ошибка валидации входных данных"),
                    @ApiResponse(responseCode = "404", description = "Бригада не найдена")
            }
    )
    EmployeeDto createEmployee(
            @RequestBody(
                    required = true,
                    description = "Данные нового сотрудника",
                    content = @Content(schema = @Schema(implementation = EmployeeDetails.class))
            )
            EmployeeDetails employeeDetails
    );

    @Operation(
            summary = "Обновить данные сотрудника",
            description = "Обновляет информацию о существующем сотруднике по ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Сотрудник успешно обновлён"),
                    @ApiResponse(responseCode = "400", description = "Ошибка валидации входных данных"),
                    @ApiResponse(responseCode = "404", description = "Сотрудник или бригада не найдены")
            }
    )
    EmployeeDto updateEmployee(
            @Parameter(description = "ID сотрудника", example = "1") Long id,
            EmployeeDetails employeeDetails
    );

}
