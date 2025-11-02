package ru.anykeyers.productionplannerstorage.exception;

import org.springframework.http.HttpStatus;

/**
 * Ошибка отсутствия параметра производительности
 */
public class OptimizationParameterNotFoundException extends PlannerStorageResponseStatusException {

    public OptimizationParameterNotFoundException(Long optimizationParameterId) {
        super(HttpStatus.NOT_FOUND, "optimization parameter not found with id: {0}" + optimizationParameterId);
    }

}
