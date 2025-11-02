package ru.anykeyers.productionplannerstorage.exception;

import org.springframework.http.HttpStatus;

/**
 * Ошибка отсутствия бригады
 */
public class TeamNotFoundException extends PlannerStorageResponseStatusException {

    public TeamNotFoundException(long teamId) {
        super(HttpStatus.NOT_FOUND, "team not found with id: {0}", teamId);
    }

}
