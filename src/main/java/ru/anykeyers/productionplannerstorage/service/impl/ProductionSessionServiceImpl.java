package ru.anykeyers.productionplannerstorage.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.anykeyers.productionplannerstorage.domain.ProductionSession;
import ru.anykeyers.productionplannerstorage.domain.SessionOrder;
import ru.anykeyers.productionplannerstorage.domain.dto.ProductionSessionDto;
import ru.anykeyers.productionplannerstorage.domain.enums.ProductionType;
import ru.anykeyers.productionplannerstorage.domain.mapper.ProductionSessionMapper;
import ru.anykeyers.productionplannerstorage.domain.request.ProductionSessionDetails;
import ru.anykeyers.productionplannerstorage.domain.Product;
import ru.anykeyers.productionplannerstorage.domain.request.SessionOrderDetails;
import ru.anykeyers.productionplannerstorage.exception.ProductNotFoundException;
import ru.anykeyers.productionplannerstorage.exception.ProductionSessionNotFoundException;
import ru.anykeyers.productionplannerstorage.repository.ProductRepository;
import ru.anykeyers.productionplannerstorage.repository.ProductionSessionRepository;
import ru.anykeyers.productionplannerstorage.service.ProductionSessionService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ProductionSessionServiceImpl implements ProductionSessionService {

    /**
     * Дней для добавления к дате начала при создании сессии
     */
    private static final int DAYS_TO_ADD = 29;

    private final ProductRepository productRepository;
    private final ProductionSessionMapper productionSessionMapper;
    private final ProductionSessionRepository productionSessionRepository;

    @Override
    public List<ProductionSessionDto> getAllProductionSessions() {
        return productionSessionMapper.toDto(productionSessionRepository.findAll());
    }

    @Override
    public ProductionSessionDto getProductionSession(long productionSessionId) throws ProductionSessionNotFoundException {
        return productionSessionMapper.toDto(getProductionSessionEntity(productionSessionId));
    }

    @Override
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

    @Override
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
        ProductionSession savedProductionSession = productionSessionRepository.save(productionSession);
        log.info("Add session order to production session: {}", savedProductionSession);
        return productionSessionMapper.toDto(savedProductionSession);
    }

    @Override
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

    @Override
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
