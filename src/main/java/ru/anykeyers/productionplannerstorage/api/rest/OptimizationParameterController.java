package ru.anykeyers.productionplannerstorage.api.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.anykeyers.productionplannerstorage.api.OptimizationParameterApi;
import ru.anykeyers.productionplannerstorage.domain.dto.OptimizationParameterDto;
import ru.anykeyers.productionplannerstorage.domain.dto.request.OptimizationParameterDetails;
import ru.anykeyers.productionplannerstorage.domain.service.OptimizationParameterService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(RestControllerPath.OPTIMIZATION_PARAMETERS)
class OptimizationParameterController implements OptimizationParameterApi {

    private final OptimizationParameterService optimizationParameterService;

    @Override
    @GetMapping
    public List<OptimizationParameterDto> getActiveOptimizationParameters() {
        return optimizationParameterService.getActiveOptimizationParameters();
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OptimizationParameterDto createOptimizationParameter(
            @RequestBody @Valid OptimizationParameterDetails optimizationParameterDetails) {
        return optimizationParameterService.createOptimizationParameter(optimizationParameterDetails);
    }

    @Override
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OptimizationParameterDto updateOptimizationParameter(@PathVariable Long id,
                                                                @RequestBody @Valid OptimizationParameterDetails optimizationParameterDetails) {
        return optimizationParameterService.updateOptimizationParameter(id, optimizationParameterDetails);
    }

}
