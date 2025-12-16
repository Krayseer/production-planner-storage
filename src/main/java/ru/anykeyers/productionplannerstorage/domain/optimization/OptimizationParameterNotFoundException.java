package ru.anykeyers.productionplannerstorage.domain.optimization;

import org.springframework.http.HttpStatus;
import ru.anykeyers.productionplannerstorage.exception.PlannerStorageResponseStatusException;

/**
 * Ошибка отсутствия параметра производительности
 */
public class OptimizationParameterNotFoundException extends PlannerStorageResponseStatusException {

    public OptimizationParameterNotFoundException(Long optimizationParameterId) {
        super(HttpStatus.NOT_FOUND, "optimization parameter not found with id: {0}" + optimizationParameterId);
    }

}
