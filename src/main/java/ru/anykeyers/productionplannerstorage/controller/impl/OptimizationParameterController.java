package ru.anykeyers.productionplannerstorage.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.anykeyers.productionplannerstorage.controller.OptimizationParameterApi;
import ru.anykeyers.productionplannerstorage.controller.dto.OptimizationParameterDto;
import ru.anykeyers.productionplannerstorage.controller.request.OptimizationParameterDetails;
import ru.anykeyers.productionplannerstorage.service.OptimizationParameterService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OptimizationParameterController implements OptimizationParameterApi {

    private final OptimizationParameterService optimizationParameterService;

    @Override
    public List<OptimizationParameterDto> getActiveOptimizationParameters() {
        return optimizationParameterService.getActiveOptimizationParameters();
    }

    @Override
    public OptimizationParameterDto createOptimizationParameter(OptimizationParameterDetails optimizationParameterDetails) {
        return optimizationParameterService.createOptimizationParameter(optimizationParameterDetails);
    }

    @Override
    public OptimizationParameterDto updateOptimizationParameter(Long id, OptimizationParameterDetails optimizationParameterDetails) {
        return optimizationParameterService.updateOptimizationParameter(id, optimizationParameterDetails);
    }

}
