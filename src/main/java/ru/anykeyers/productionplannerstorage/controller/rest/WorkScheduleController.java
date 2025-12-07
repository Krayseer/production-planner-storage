package ru.anykeyers.productionplannerstorage.controller.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.anykeyers.productionplannerstorage.controller.WorkScheduleApi;
import ru.anykeyers.productionplannerstorage.domain.dto.WorkScheduleDto;
import ru.anykeyers.productionplannerstorage.domain.request.WorkScheduleDetails;
import ru.anykeyers.productionplannerstorage.service.WorkScheduleService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(RestControllerPath.WORK_SCHEDULES)
public class WorkScheduleController implements WorkScheduleApi {

    private final WorkScheduleService workScheduleService;

    @Override
    @GetMapping
    public List<WorkScheduleDto> getWorkSchedules() {
        return workScheduleService.getWorkSchedules();
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WorkScheduleDto createWorkSchedule(@RequestBody @Valid WorkScheduleDetails workScheduleDetails) {
        return workScheduleService.createWorkSchedule(workScheduleDetails);
    }

    @Override
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public WorkScheduleDto updateWorkSchedule(@PathVariable Long id,
                                              @RequestBody @Valid WorkScheduleDetails workScheduleDetails) {
        return workScheduleService.updateWorkSchedule(id, workScheduleDetails);
    }

}
