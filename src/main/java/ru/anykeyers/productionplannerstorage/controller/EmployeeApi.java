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
import ru.anykeyers.productionplannerstorage.controller.dto.EmployeeDto;
import ru.anykeyers.productionplannerstorage.controller.request.EmployeeDetails;

import java.util.List;

@Tag(name = "Employees", description = "API для управления сотрудниками")
@RequestMapping(ControllerPath.EMPLOYEES)
public interface EmployeeApi {

    @Operation(
            summary = "Получить всех сотрудников",
            description = "Возвращает список всех сотрудников системы",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Список сотрудников успешно получен",
                            content = @Content(schema = @Schema(implementation = EmployeeDto.class)))
            }
    )
    @GetMapping
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
    @GetMapping("/team/{teamId}")
    List<EmployeeDto> getEmployeesByTeamId(
            @Parameter(description = "ID бригады", example = "1") @PathVariable Long teamId
    );

    @Operation(
            summary = "Создать нового сотрудника",
            description = "Создаёт нового сотрудника и возвращает созданный объект",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Сотрудник успешно создан"),
                    @ApiResponse(responseCode = "400", description = "Ошибка валидации входных данных"),
                    @ApiResponse(responseCode = "404", description = "Бригада не найдена")
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    EmployeeDto createEmployee(
            @RequestBody(
                    required = true,
                    description = "Данные нового сотрудника",
                    content = @Content(schema = @Schema(implementation = EmployeeDetails.class))
            )
            @org.springframework.web.bind.annotation.RequestBody @Valid EmployeeDetails employeeDetails
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
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    EmployeeDto updateEmployee(
            @Parameter(description = "ID сотрудника", example = "1") @PathVariable Long id,
            @org.springframework.web.bind.annotation.RequestBody @Valid EmployeeDetails employeeDetails
    );

}
