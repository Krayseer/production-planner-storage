package ru.anykeyers.productionplannerstorage.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.anykeyers.productionplannerstorage.controller.AssemblyScheduleApi;
import ru.anykeyers.productionplannerstorage.domain.dto.AssemblyScheduleDto;
import ru.anykeyers.productionplannerstorage.domain.request.AssemblyScheduleDetails;
import ru.anykeyers.productionplannerstorage.service.AssemblyScheduleService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AssemblyScheduleController implements AssemblyScheduleApi {

    private final AssemblyScheduleService assemblyScheduleService;

    @Override
    public List<AssemblyScheduleDto> getAssemblySchedules() {
        return assemblyScheduleService.getAssemblySchedules();
    }

    @Override
    public AssemblyScheduleDto createAssemblySchedule(AssemblyScheduleDetails assemblyScheduleDetails) {
        return assemblyScheduleService.createAssemblySchedule(assemblyScheduleDetails);
    }

    @Override
    public AssemblyScheduleDto updateAssemblySchedule(Long assemblyScheduleId, AssemblyScheduleDetails assemblyScheduleDetails) {
        return assemblyScheduleService.updateAssemblySchedule(assemblyScheduleId, assemblyScheduleDetails);
    }

}
