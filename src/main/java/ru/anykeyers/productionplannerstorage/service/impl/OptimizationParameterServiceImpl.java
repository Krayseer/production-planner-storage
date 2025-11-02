package ru.anykeyers.productionplannerstorage.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.anykeyers.productionplannerstorage.controller.dto.OptimizationParameterDto;
import ru.anykeyers.productionplannerstorage.controller.mapper.OptimizationParameterMapper;
import ru.anykeyers.productionplannerstorage.controller.request.OptimizationParameterDetails;
import ru.anykeyers.productionplannerstorage.domain.OptimizationParameter;
import ru.anykeyers.productionplannerstorage.exception.OptimizationParameterNotFoundException;
import ru.anykeyers.productionplannerstorage.repository.OptimizationParameterRepository;
import ru.anykeyers.productionplannerstorage.service.OptimizationParameterService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OptimizationParameterServiceImpl implements OptimizationParameterService {

    private final OptimizationParameterMapper optimizationParameterMapper;
    private final OptimizationParameterRepository optimizationParameterRepository;

    @Override
    public List<OptimizationParameterDto> getActiveOptimizationParameters() {
        return optimizationParameterMapper.toDto(optimizationParameterRepository.findAllByActiveIsTrue());
    }

    @Override
    public OptimizationParameterDto createOptimizationParameter(OptimizationParameterDetails optimizationParameterDetails) {
        OptimizationParameter optimizationParameter = OptimizationParameter.builder()
                .alphaCoefficient(optimizationParameterDetails.alphaCoefficient())
                .maxTeamsPerDay(optimizationParameterDetails.maxTeamsPerDay())
                .maxHoursPerShift(optimizationParameterDetails.maxHoursPerShift())
                .loadBalanceTolerance(optimizationParameterDetails.loadBalanceTolerance())
                .active(optimizationParameterDetails.active())
                .build();
        OptimizationParameter savedOptimizationParameter = optimizationParameterRepository.save(optimizationParameter);
        log.info("Created optimization parameter: {}", savedOptimizationParameter);
        return optimizationParameterMapper.toDto(savedOptimizationParameter);
    }

    @Override
    public OptimizationParameterDto updateOptimizationParameter(Long optimizationParameterId, OptimizationParameterDetails optimizationParameterDetails)
            throws OptimizationParameterNotFoundException {
        OptimizationParameter optimizationParameter = optimizationParameterRepository.findById(optimizationParameterId)
                .orElseThrow(() -> new OptimizationParameterNotFoundException(optimizationParameterId));
        optimizationParameter.setAlphaCoefficient(optimizationParameterDetails.alphaCoefficient());
        optimizationParameter.setMaxTeamsPerDay(optimizationParameterDetails.maxTeamsPerDay());
        optimizationParameter.setMaxHoursPerShift(optimizationParameterDetails.maxHoursPerShift());
        optimizationParameter.setActive(optimizationParameterDetails.active());
        OptimizationParameter savedOptimizationParameter = optimizationParameterRepository.save(optimizationParameter);
        log.info("Updated optimization parameter: {}", savedOptimizationParameter);
        return optimizationParameterMapper.toDto(savedOptimizationParameter);
    }

}
