package ru.anykeyers.productionplannerstorage.domain.session;

import org.springframework.http.HttpStatus;
import ru.anykeyers.productionplannerstorage.exception.PlannerStorageResponseStatusException;

/**
 * Ошибка отсутствия производственной сессии
 */
public class ProductionSessionNotFoundException extends PlannerStorageResponseStatusException {

    public ProductionSessionNotFoundException(long productionSessionId) {
        super(HttpStatus.NOT_FOUND, "production session not found with id: {0}", productionSessionId);
    }

}
