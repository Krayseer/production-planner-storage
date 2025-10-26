package ru.anykeyers.productionplannerstorage.service;

import ru.anykeyers.productionplannerstorage.controller.dto.ProductionPlanDto;
import ru.anykeyers.productionplannerstorage.controller.request.ProductionPlanDetails;
import ru.anykeyers.productionplannerstorage.exception.ProductNotFoundException;
import ru.anykeyers.productionplannerstorage.exception.ProductionPlanNotFoundException;

import java.util.List;

/**
 * Сервис планов производства
 */
public interface ProductionPlanService {

    /**
     * @return список всех планов производства
     */
    List<ProductionPlanDto> getAllProductionPlans();

    /**
     * Получить план производства
     *
     * @param productionPlanId идентификатор плана производства
     */
    ProductionPlanDto getProductionPlan(long productionPlanId)
            throws ProductionPlanNotFoundException, ProductNotFoundException;

    /**
     * Создать план производства
     *
     * @param productionPlanDetails данные о плане производства
     */
    ProductionPlanDto createProductionPlan(ProductionPlanDetails productionPlanDetails);

    /**
     * Обновить план производства
     *
     * @param productionPlanId      идентификатор плана производства
     * @param productionPlanDetails обновленные данные о плане производства
     * @return обновленный план производства
     */
    ProductionPlanDto updateProductionPlan(long productionPlanId, ProductionPlanDetails productionPlanDetails)
            throws ProductionPlanNotFoundException, ProductNotFoundException;

    /**
     * Удалить план производства
     *
     * @param productionPlanId  идентификатор плана производства
     */
    void deleteProductionPlan(long productionPlanId);

}
