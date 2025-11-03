package ru.anykeyers.productionplannerstorage.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.anykeyers.productionplannerstorage.controller.WorkScheduleApi;
import ru.anykeyers.productionplannerstorage.domain.dto.WorkScheduleDto;
import ru.anykeyers.productionplannerstorage.domain.request.WorkScheduleDetails;
import ru.anykeyers.productionplannerstorage.service.WorkScheduleService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WorkScheduleController implements WorkScheduleApi {

    private final WorkScheduleService workScheduleService;

    @Override
    public List<WorkScheduleDto> getWorkSchedules() {
        return workScheduleService.getWorkSchedules();
    }

    @Override
    public WorkScheduleDto createWorkSchedule(WorkScheduleDetails workScheduleDetails) {
        return workScheduleService.createWorkSchedule(workScheduleDetails);
    }

    @Override
    public WorkScheduleDto updateWorkSchedule(Long id, WorkScheduleDetails workScheduleDetails) {
        return workScheduleService.updateWorkSchedule(id, workScheduleDetails);
    }

}
