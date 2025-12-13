package ru.anykeyers.productionplannerstorage.api.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.anykeyers.productionplannerstorage.api.AssemblyScheduleApi;
import ru.anykeyers.productionplannerstorage.domain.dto.AssemblyScheduleDto;
import ru.anykeyers.productionplannerstorage.domain.dto.request.AssemblyScheduleDetails;
import ru.anykeyers.productionplannerstorage.domain.service.AssemblyScheduleService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(RestControllerPath.ASSEMBLY_SCHEDULES)
class AssemblyScheduleController implements AssemblyScheduleApi {

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
