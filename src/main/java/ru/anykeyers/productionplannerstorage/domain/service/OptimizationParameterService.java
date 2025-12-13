package ru.anykeyers.productionplannerstorage.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.anykeyers.productionplannerstorage.domain.DtoMapper;
import ru.anykeyers.productionplannerstorage.domain.dto.OptimizationParameterDto;
import ru.anykeyers.productionplannerstorage.domain.dto.request.OptimizationParameterDetails;
import ru.anykeyers.productionplannerstorage.domain.model.OptimizationParameter;
import ru.anykeyers.productionplannerstorage.exception.OptimizationParameterNotFoundException;
import ru.anykeyers.productionplannerstorage.infrastructure.database.OptimizationParameterRepository;

import java.util.List;

/**
 * Сервис параметров производительности
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OptimizationParameterService {

    private final OptimizationParameterRepository optimizationParameterRepository;
    private final DtoMapper<OptimizationParameter, OptimizationParameterDto> optimizationParameterMapper;

    /**
     * @return список активных параметров
     */
    public List<OptimizationParameterDto> getActiveOptimizationParameters() {
        return optimizationParameterMapper.toDto(optimizationParameterRepository.findAllByActiveIsTrue());
    }

    /**
     * Создать параметр оптимизации
     *
     * @param optimizationParameterDetails данные о параметре оптимизации
     */
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

    /**
     * Обновить параметр оптимизации
     *
     * @param optimizationParameterId       идентификатор параметра оптимизации
     * @param optimizationParameterDetails  обновленные данные о параметре оптимизации
     */
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
