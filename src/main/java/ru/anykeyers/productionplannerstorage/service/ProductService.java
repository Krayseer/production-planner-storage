package ru.anykeyers.productionplannerstorage.service;

import ru.anykeyers.productionplannerstorage.controller.dto.ProductDto;
import ru.anykeyers.productionplannerstorage.controller.request.ProductDetails;
import ru.anykeyers.productionplannerstorage.exception.ProductNotFoundException;

import java.util.List;

/**
 * Сервис продуктов
 */
public interface ProductService {

    /**
     * @return список всех изделий
     */
    List<ProductDto> getAllProducts();

    /**
     * Получить продукт
     *
     * @param productId идентификатор продукта
     */
    ProductDto getProduct(long productId) throws ProductNotFoundException;

    /**
     * Создать продукт
     *
     * @param productDetails данные о продукте
     * @return созданный продукт
     */
    ProductDto createProduct(ProductDetails productDetails);

    /**
     * Обновить продукт
     *
     * @param productId         идентификатор продукта
     * @param productDetails    обновленные данные о продукте
     * @return обновленный продукт
     */
    ProductDto updateProduct(long productId, ProductDetails productDetails) throws ProductNotFoundException;

    /**
     * Удалить продукт
     *
     * @param productId идентификатор продукта
     */
    void deleteProduct(long productId);

}
