package ru.anykeyers.productionplannerstorage.service;

import ru.anykeyers.productionplannerstorage.domain.dto.OptimizationParameterDto;
import ru.anykeyers.productionplannerstorage.domain.request.OptimizationParameterDetails;
import ru.anykeyers.productionplannerstorage.exception.OptimizationParameterNotFoundException;

import java.util.List;

/**
 * Сервис параметров производительности
 */
public interface OptimizationParameterService {

    /**
     * @return список активных параметров
     */
    List<OptimizationParameterDto> getActiveOptimizationParameters();

    /**
     * Создать параметр оптимизации
     *
     * @param optimizationParameterDetails данные о параметре оптимизации
     */
    OptimizationParameterDto createOptimizationParameter(OptimizationParameterDetails optimizationParameterDetails);

    /**
     * Обновить параметр оптимизации
     *
     * @param optimizationParameterId       идентификатор параметра оптимизации
     * @param optimizationParameterDetails  обновленные данные о параметре оптимизации
     */
    OptimizationParameterDto updateOptimizationParameter(Long optimizationParameterId, OptimizationParameterDetails optimizationParameterDetails)
            throws OptimizationParameterNotFoundException;

}
