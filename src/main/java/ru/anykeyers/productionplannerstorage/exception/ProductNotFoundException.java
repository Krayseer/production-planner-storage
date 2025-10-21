package ru.anykeyers.productionplannerstorage.exception;

import org.springframework.http.HttpStatus;

/**
 * Ошибка отсутствия продукта
 */
public class ProductNotFoundException extends PlannerStorageResponseStatusException {

    public ProductNotFoundException(long productId) {
        super(HttpStatus.NOT_FOUND, "product not found with id: {0}", productId);
    }

}
