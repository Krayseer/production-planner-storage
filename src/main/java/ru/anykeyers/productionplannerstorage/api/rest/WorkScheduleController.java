package ru.anykeyers.productionplannerstorage.api.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.anykeyers.productionplannerstorage.api.WorkScheduleApi;
import ru.anykeyers.productionplannerstorage.domain.workshedule.WorkScheduleDto;
import ru.anykeyers.productionplannerstorage.domain.workshedule.WorkScheduleDetails;
import ru.anykeyers.productionplannerstorage.domain.workshedule.WorkScheduleService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(RestControllerPath.WORK_SCHEDULES)
class WorkScheduleController implements WorkScheduleApi {

    private final WorkScheduleService workScheduleService;

    @Override
    @GetMapping
    public List<WorkScheduleDto> getWorkSchedules() {
        return workScheduleService.getWorkSchedules();
    }

    @Override
    @PostMapping
    public WorkScheduleDto createWorkSchedule(@RequestBody @Valid WorkScheduleDetails workScheduleDetails) {
        return workScheduleService.createWorkSchedule(workScheduleDetails);
    }

    @Override
    @PutMapping("/{id}")
    public WorkScheduleDto updateWorkSchedule(@PathVariable Long id,
                                              @RequestBody @Valid WorkScheduleDetails workScheduleDetails) {
        return workScheduleService.updateWorkSchedule(id, workScheduleDetails);
    }

}
