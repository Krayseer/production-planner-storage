package ru.anykeyers.productionplannerstorage.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.anykeyers.productionplannerstorage.domain.DtoMapper;
import ru.anykeyers.productionplannerstorage.domain.model.ProductionSession;
import ru.anykeyers.productionplannerstorage.domain.model.SessionOrder;
import ru.anykeyers.productionplannerstorage.domain.dto.ProductionSessionDto;
import ru.anykeyers.productionplannerstorage.domain.model.ProductionType;
import ru.anykeyers.productionplannerstorage.domain.dto.request.ProductionSessionDetails;
import ru.anykeyers.productionplannerstorage.domain.model.Product;
import ru.anykeyers.productionplannerstorage.domain.dto.request.SessionOrderDetails;
import ru.anykeyers.productionplannerstorage.exception.ProductNotFoundException;
import ru.anykeyers.productionplannerstorage.exception.ProductionSessionNotFoundException;
import ru.anykeyers.productionplannerstorage.infrastructure.database.ProductRepository;
import ru.anykeyers.productionplannerstorage.infrastructure.database.ProductionSessionRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис производственных сессий
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ProductionSessionService {

    /**
     * Дней для добавления к дате начала при создании сессии
     */
    private static final int DAYS_TO_ADD = 29;

    private final ProductRepository productRepository;
    private final ProductionSessionRepository productionSessionRepository;
    private final DtoMapper<ProductionSession, ProductionSessionDto> productionSessionMapper;

    /**
     * @return список всех производственных сессий
     */
    public List<ProductionSessionDto> getAllProductionSessions() {
        return productionSessionMapper.toDto(productionSessionRepository.findAll());
    }

    /**
     * Получить производственную сессию
     *
     * @param productionSessionId идентификатор производственной сессии
     */
    public ProductionSessionDto getProductionSession(long productionSessionId) throws ProductionSessionNotFoundException {
        return productionSessionMapper.toDto(getProductionSessionEntity(productionSessionId));
    }

    /**
     * Создать производственную сессию
     *
     * @param productionSessionDetails данные о плане производства
     */
    public ProductionSessionDto createProductionSession(ProductionSessionDetails productionSessionDetails) {
        ProductionSession productionSession = ProductionSession.builder()
                .name(productionSessionDetails.name())
                .startDate(productionSessionDetails.startDate())
                .endDate(productionSessionDetails.startDate().plusDays(DAYS_TO_ADD))
                .build();
        ProductionSession savedProductionSession = productionSessionRepository.save(productionSession);
        log.info("Created production session: {}", savedProductionSession);
        return productionSessionMapper.toDto(savedProductionSession);
    }

    /**
     * Создать заказ в сессии
     *
     * @param productionSessionId   идентификатор производственной сессии
     * @param sessionOrderDetails   данные о заказе сессии
     */
    public ProductionSessionDto createProductionSessionOrder(Long productionSessionId, SessionOrderDetails sessionOrderDetails) {
        Product product = getProductEntity(sessionOrderDetails.productId());
        ProductionSession productionSession = getProductionSessionEntity(productionSessionId);
        SessionOrder sessionOrder = SessionOrder.builder()
                .product(product)
                .productionType(ProductionType.valueOf(sessionOrderDetails.productionType().toUpperCase()))
                .quantity(sessionOrderDetails.quantity())
                .deadlineDate(sessionOrderDetails.deadlineDate())
                .source(sessionOrderDetails.source())
                .build();
        if (productionSession.getSessionOrders() == null) {
            productionSession.setSessionOrders(new ArrayList<>());
        }
        productionSession.getSessionOrders().add(sessionOrder);
        sessionOrder.setSession(productionSession);
        ProductionSession savedProductionSession = productionSessionRepository.save(productionSession);
        log.info("Add session order to production session: {}", savedProductionSession);
        return productionSessionMapper.toDto(savedProductionSession);
    }

    /**
     * Обновить производственную сессию
     *
     * @param productionSessionId       идентификатор плана производства
     * @param productionSessionDetails  обновленные данные о производственной сессии
     * @return обновленный план производства
     */
    public ProductionSessionDto updateProductionSession(long productionSessionId, ProductionSessionDetails productionSessionDetails)
            throws ProductionSessionNotFoundException, ProductNotFoundException {
        ProductionSession productionSession = getProductionSessionEntity(productionSessionId);
        productionSession.setName(productionSessionDetails.name());
        productionSession.setStartDate(productionSessionDetails.startDate());
        productionSession.setEndDate(productionSessionDetails.startDate().plusDays(DAYS_TO_ADD));
        ProductionSession updatedProductionSession = productionSessionRepository.save(productionSession);
        log.info("Updated production session: {}", updatedProductionSession);
        return productionSessionMapper.toDto(updatedProductionSession);
    }

    /**
     * Удалить производственную сессию
     *
     * @param productionSessionId идентификатор производственной сессии
     */
    public void deleteProductionSession(long productionSessionId) {
        productionSessionRepository.deleteById(productionSessionId);
    }

    private Product getProductEntity(long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
    }

    private ProductionSession getProductionSessionEntity(long productionPlanId) {
        return productionSessionRepository.findById(productionPlanId).orElseThrow(() ->
                new ProductionSessionNotFoundException(productionPlanId));
    }

}
