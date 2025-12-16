package ru.anykeyers.productionplannerstorage.domain.optimization.optimizer;

/**
 * Оптимизатор
 */
public interface Optimizer {

    /**
     * Выполнить оптимизацию
     *
     * @param optimizerRequest данные для оптимизации
     * @return результат оптимизации
     */
    OptimizerResult optimize(OptimizerRequest optimizerRequest);

}
