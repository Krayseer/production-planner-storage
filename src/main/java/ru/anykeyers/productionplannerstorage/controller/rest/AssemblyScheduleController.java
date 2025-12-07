package ru.anykeyers.productionplannerstorage.controller.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.anykeyers.productionplannerstorage.controller.AssemblyScheduleApi;
import ru.anykeyers.productionplannerstorage.domain.dto.AssemblyScheduleDto;
import ru.anykeyers.productionplannerstorage.domain.request.AssemblyScheduleDetails;
import ru.anykeyers.productionplannerstorage.service.AssemblyScheduleService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(RestControllerPath.ASSEMBLY_SCHEDULES)
public class AssemblyScheduleController implements AssemblyScheduleApi {

    private final AssemblyScheduleService assemblyScheduleService;

    @Override
    @GetMapping
    public List<AssemblyScheduleDto> getAssemblySchedules() {
        return assemblyScheduleService.getAssemblySchedules();
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AssemblyScheduleDto createAssemblySchedule(@RequestBody @Valid AssemblyScheduleDetails assemblyScheduleDetails) {
        return assemblyScheduleService.createAssemblySchedule(assemblyScheduleDetails);
    }

    @Override
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AssemblyScheduleDto updateAssemblySchedule(@PathVariable("id") Long assemblyScheduleId,
                                                      @RequestBody @Valid AssemblyScheduleDetails assemblyScheduleDetails) {
        return assemblyScheduleService.updateAssemblySchedule(assemblyScheduleId, assemblyScheduleDetails);
    }

}
