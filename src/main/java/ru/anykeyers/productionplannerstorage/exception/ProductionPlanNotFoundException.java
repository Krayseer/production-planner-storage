package ru.anykeyers.productionplannerstorage.exception;

import org.springframework.http.HttpStatus;

/**
 * Ошибка отсутствия плана производства
 */
public class ProductionPlanNotFoundException extends PlannerStorageResponseStatusException {

    public ProductionPlanNotFoundException(long productionPlanId) {
        super(HttpStatus.NOT_FOUND, "production plan not found with id: {0}", productionPlanId);
    }

}
