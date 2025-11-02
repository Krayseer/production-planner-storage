package ru.anykeyers.productionplannerstorage.exception;

import org.springframework.http.HttpStatus;

/**
 * Ошибка отсутствия записи производительности бригады
 */
public class TeamProductivityNotFoundException extends PlannerStorageResponseStatusException {

    public TeamProductivityNotFoundException(Long teamProductivityId) {
        super(HttpStatus.NOT_FOUND, "team productivity not found with id: {0}", teamProductivityId);
    }

}
