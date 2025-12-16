package ru.anykeyers.productionplannerstorage.domain.optimization.optimizer;

import ru.anykeyers.productionplannerstorage.domain.optimization.result.OptimizationResult;
import ru.anykeyers.productionplannerstorage.domain.optimization.run.OptimizationRun;

import java.util.List;

/**
 * Оптимизатор
 */
public interface Optimizer {

    /**
     * Выполнить оптимизацию
     */
    List<OptimizationResult> optimize(OptimizationRun optimizationRun);

}
