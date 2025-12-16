package ru.anykeyers.productionplannerstorage.domain.team;

import org.springframework.http.HttpStatus;
import ru.anykeyers.productionplannerstorage.exception.PlannerStorageResponseStatusException;

/**
 * Ошибка отсутствия бригады
 */
public class TeamNotFoundException extends PlannerStorageResponseStatusException {

    public TeamNotFoundException(long teamId) {
        super(HttpStatus.NOT_FOUND, "team not found with id: {0}", teamId);
    }

}
