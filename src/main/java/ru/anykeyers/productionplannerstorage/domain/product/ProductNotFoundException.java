package ru.anykeyers.productionplannerstorage.domain.product;

import org.springframework.http.HttpStatus;
import ru.anykeyers.productionplannerstorage.exception.PlannerStorageResponseStatusException;

/**
 * Ошибка отсутствия продукта
 */
public class ProductNotFoundException extends PlannerStorageResponseStatusException {

    public ProductNotFoundException(long productId) {
        super(HttpStatus.NOT_FOUND, "product not found with id: {0}", productId);
    }

}
