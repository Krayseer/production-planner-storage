package ru.anykeyers.productionplannerstorage.domain.team.productivity;

import org.springframework.http.HttpStatus;
import ru.anykeyers.productionplannerstorage.exception.PlannerStorageResponseStatusException;

/**
 * Ошибка отсутствия записи производительности бригады
 */
public class TeamProductivityNotFoundException extends PlannerStorageResponseStatusException {

    public TeamProductivityNotFoundException(Long teamProductivityId) {
        super(HttpStatus.NOT_FOUND, "team productivity not found with id: {0}", teamProductivityId);
    }

}
