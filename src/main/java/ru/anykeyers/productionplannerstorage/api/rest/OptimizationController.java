package ru.anykeyers.productionplannerstorage.api.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.anykeyers.productionplannerstorage.api.OptimizationApi;
import ru.anykeyers.productionplannerstorage.domain.optimization.result.OptimizationResultDto;
import ru.anykeyers.productionplannerstorage.domain.optimization.result.OptimizationResultMapper;
import ru.anykeyers.productionplannerstorage.domain.optimization.run.OptimizationRunDto;
import ru.anykeyers.productionplannerstorage.domain.optimization.run.OptimizationRunDetails;
import ru.anykeyers.productionplannerstorage.domain.optimization.OptimizationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(RestControllerPath.OPTIMIZATION)
class OptimizationController implements OptimizationApi {

    private final OptimizationService optimizationService;

    @Override
    @GetMapping
    public List<OptimizationRunDto> getActiveOptimizationRuns() {
        return optimizationService.getActiveOptimizationParameters();
    }

    @Override
    @PostMapping
    public OptimizationRunDto createOptimizationRun(@RequestBody @Valid OptimizationRunDetails optimizationRunDetails) {
        return optimizationService.createOptimizationRun(optimizationRunDetails);
    }

    @PostMapping("/optimize/{optimizationRunId}")
    public List<OptimizationResultDto> optimize(@PathVariable Long optimizationRunId) {
        return optimizationService.optimize(optimizationRunId);
    }

    @Override
    @PutMapping("/{id}")
    public OptimizationRunDto updateOptimizationRun(@PathVariable Long id,
                                                    @RequestBody @Valid OptimizationRunDetails optimizationRunDetails) {
        return optimizationService.updateOptimizationParameter(id, optimizationRunDetails);
    }

}
