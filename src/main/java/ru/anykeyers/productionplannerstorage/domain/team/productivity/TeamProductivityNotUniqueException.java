package ru.anykeyers.productionplannerstorage.domain.team.productivity;

import org.springframework.http.HttpStatus;
import ru.anykeyers.productionplannerstorage.exception.PlannerStorageResponseStatusException;

/**
 * Ошибка уникальности комбинации (teamId, productId, productionType)
 */
public class TeamProductivityNotUniqueException extends PlannerStorageResponseStatusException {

    public TeamProductivityNotUniqueException(Long teamId, Long productId, String productionType) {
        super(HttpStatus.BAD_REQUEST, "Team productivity with team_id={0}, product_id={1}, production_type={2} already exists",
                teamId, productId, productionType);
    }

}
