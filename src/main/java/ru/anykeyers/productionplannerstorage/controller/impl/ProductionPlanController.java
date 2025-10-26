package ru.anykeyers.productionplannerstorage.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.anykeyers.productionplannerstorage.controller.ProductionPlanApi;
import ru.anykeyers.productionplannerstorage.controller.dto.ProductionPlanDto;
import ru.anykeyers.productionplannerstorage.controller.request.ProductionPlanDetails;
import ru.anykeyers.productionplannerstorage.service.ProductionPlanService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductionPlanController implements ProductionPlanApi {

    private final ProductionPlanService productionPlanService;

    @Override
    public List<ProductionPlanDto> getAllProductionPlans() {
        return productionPlanService.getAllProductionPlans();
    }

    @Override
    public ProductionPlanDto getProductionPlan(Long id) {
        return productionPlanService.getProductionPlan(id);
    }

    @Override
    public ProductionPlanDto createProductionPlan(ProductionPlanDetails productionPlanDetails) {
        return productionPlanService.createProductionPlan(productionPlanDetails);
    }

    @Override
    public ProductionPlanDto updateProductionPlan(Long id, ProductionPlanDetails productionPlanDetails) {
        return productionPlanService.updateProductionPlan(id, productionPlanDetails);
    }

    @Override
    public void deleteProductionPlan(Long id) {
        productionPlanService.deleteProductionPlan(id);
    }

}
