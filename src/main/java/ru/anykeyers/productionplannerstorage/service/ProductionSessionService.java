package ru.anykeyers.productionplannerstorage.service;

import jakarta.validation.Valid;
import ru.anykeyers.productionplannerstorage.domain.dto.ProductionSessionDto;
import ru.anykeyers.productionplannerstorage.domain.request.ProductionSessionDetails;
import ru.anykeyers.productionplannerstorage.domain.request.SessionOrderDetails;
import ru.anykeyers.productionplannerstorage.exception.ProductNotFoundException;
import ru.anykeyers.productionplannerstorage.exception.ProductionSessionNotFoundException;

import java.util.List;

/**
 * Сервис производственных сессий
 */
public interface ProductionSessionService {

    /**
     * @return список всех производственных сессий
     */
    List<ProductionSessionDto> getAllProductionSessions();

    /**
     * Получить производственную сессию
     *
     * @param productionSessionId идентификатор производственной сессии
     */
    ProductionSessionDto getProductionSession(long productionSessionId)
            throws ProductionSessionNotFoundException, ProductNotFoundException;

    /**
     * Создать производственную сессию
     *
     * @param productionSessionDetails данные о плане производства
     */
    ProductionSessionDto createProductionSession(ProductionSessionDetails productionSessionDetails);

    /**
     * Создать заказ в сессии
     *
     * @param productionSessionId   идентификатор производственной сессии
     * @param sessionOrderDetails   данные о заказе сессии
     */
    ProductionSessionDto createProductionSessionOrder(Long productionSessionId, @Valid SessionOrderDetails sessionOrderDetails);

    /**
     * Обновить производственную сессию
     *
     * @param productionSessionId       идентификатор плана производства
     * @param productionSessionDetails  обновленные данные о производственной сессии
     * @return обновленный план производства
     */
    ProductionSessionDto updateProductionSession(long productionSessionId, ProductionSessionDetails productionSessionDetails)
            throws ProductionSessionNotFoundException, ProductNotFoundException;

    /**
     * Удалить производственную сессию
     *
     * @param productionSessionId идентификатор производственной сессии
     */
    void deleteProductionSession(long productionSessionId);

}
