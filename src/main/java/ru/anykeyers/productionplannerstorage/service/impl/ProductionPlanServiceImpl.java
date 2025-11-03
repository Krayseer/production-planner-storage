package ru.anykeyers.productionplannerstorage.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.anykeyers.productionplannerstorage.domain.dto.ProductionPlanDto;
import ru.anykeyers.productionplannerstorage.domain.mapper.ProductionPlanMapper;
import ru.anykeyers.productionplannerstorage.domain.request.ProductionPlanDetails;
import ru.anykeyers.productionplannerstorage.domain.Product;
import ru.anykeyers.productionplannerstorage.domain.ProductionPlan;
import ru.anykeyers.productionplannerstorage.domain.enums.ProductionType;
import ru.anykeyers.productionplannerstorage.exception.ProductNotFoundException;
import ru.anykeyers.productionplannerstorage.exception.ProductionPlanNotFoundException;
import ru.anykeyers.productionplannerstorage.repository.ProductRepository;
import ru.anykeyers.productionplannerstorage.repository.ProductionPlanRepository;
import ru.anykeyers.productionplannerstorage.service.ProductionPlanService;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ProductionPlanServiceImpl implements ProductionPlanService {

    private final ProductRepository productRepository;
    private final ProductionPlanMapper productionPlanMapper;
    private final ProductionPlanRepository productionPlanRepository;

    @Override
    public List<ProductionPlanDto> getAllProductionPlans() {
        return productionPlanMapper.toDto(productionPlanRepository.findAll());
    }

    @Override
    public ProductionPlanDto getProductionPlan(long productionPlanId) throws ProductionPlanNotFoundException {
        return productionPlanMapper.toDto(getProductionPlanEntity(productionPlanId));
    }

    @Override
    public ProductionPlanDto createProductionPlan(ProductionPlanDetails productionPlanDetails) {
        Product product = productRepository.findById(productionPlanDetails.productId()).orElseThrow(() ->
                new ProductNotFoundException(productionPlanDetails.productId()));
        ProductionPlan productionPlan = ProductionPlan.builder()
                .product(product)
                .productionType(ProductionType.valueOf(productionPlanDetails.productionType().toUpperCase()))
                .quantity(productionPlanDetails.quantity())
                .periodMonths(productionPlanDetails.periodMonths())
                .priority(productionPlanDetails.priority())
                .deadlineDate(productionPlanDetails.deadlineDate())
                .build();
        ProductionPlan savedProductionPlan = productionPlanRepository.save(productionPlan);
        log.info("Created production plan: {}", savedProductionPlan);
        return productionPlanMapper.toDto(savedProductionPlan);
    }

    @Override
    public ProductionPlanDto updateProductionPlan(long productionPlanId, ProductionPlanDetails productionPlanDetails)
            throws ProductionPlanNotFoundException {
        ProductionPlan productionPlan = getProductionPlanEntity(productionPlanId);
        productionPlan.setProduct(getProductEntity(productionPlanDetails.productId()));
        productionPlan.setQuantity(productionPlanDetails.quantity());
        productionPlan.setPeriodMonths(productionPlanDetails.periodMonths());
        productionPlan.setPriority(productionPlanDetails.priority());
        productionPlan.setDeadlineDate(productionPlanDetails.deadlineDate());
        ProductionPlan updatedProductionPlan = productionPlanRepository.save(productionPlan);
        log.info("Updated production plan: {}", updatedProductionPlan);
        return productionPlanMapper.toDto(updatedProductionPlan);
    }

    @Override
    public void deleteProductionPlan(long productionPlanId) {
        productionPlanRepository.deleteById(productionPlanId);
    }

    private Product getProductEntity(long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
    }

    private ProductionPlan getProductionPlanEntity(long productionPlanId) {
        return productionPlanRepository.findById(productionPlanId).orElseThrow(() ->
                new ProductionPlanNotFoundException(productionPlanId));
    }

}
