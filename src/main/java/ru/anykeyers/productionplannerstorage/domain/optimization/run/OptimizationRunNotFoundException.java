package ru.anykeyers.productionplannerstorage.domain.optimization.run;

import org.springframework.http.HttpStatus;
import ru.anykeyers.productionplannerstorage.exception.PlannerStorageResponseStatusException;

/**
 * Ошибка отсутствия параметра производительности
 */
public class OptimizationRunNotFoundException extends PlannerStorageResponseStatusException {

    public OptimizationRunNotFoundException(Long optimizationParameterId) {
        super(HttpStatus.NOT_FOUND, "optimization run not found with id: {0}" + optimizationParameterId);
    }

}
