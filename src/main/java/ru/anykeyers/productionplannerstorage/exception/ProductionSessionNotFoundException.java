package ru.anykeyers.productionplannerstorage.exception;

import org.springframework.http.HttpStatus;

/**
 * Ошибка отсутствия производственной сессии
 */
public class ProductionSessionNotFoundException extends PlannerStorageResponseStatusException {

    public ProductionSessionNotFoundException(long productionSessionId) {
        super(HttpStatus.NOT_FOUND, "production session not found with id: {0}", productionSessionId);
    }

}
