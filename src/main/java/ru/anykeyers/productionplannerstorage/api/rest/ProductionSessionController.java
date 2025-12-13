package ru.anykeyers.productionplannerstorage.api.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.anykeyers.productionplannerstorage.api.ProductionSessionApi;
import ru.anykeyers.productionplannerstorage.domain.dto.ProductionSessionDto;
import ru.anykeyers.productionplannerstorage.domain.dto.request.ProductionSessionDetails;
import ru.anykeyers.productionplannerstorage.domain.dto.request.SessionOrderDetails;
import ru.anykeyers.productionplannerstorage.domain.service.ProductionSessionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(RestControllerPath.PRODUCTION_SESSIONS)
class ProductionSessionController implements ProductionSessionApi {

    private final ProductionSessionService productionSessionService;

    @Override
    @GetMapping
    public List<ProductionSessionDto> getAllProductionSessions() {
        return productionSessionService.getAllProductionSessions();
    }

    @Override
    @GetMapping("/{id}")
    public ProductionSessionDto getProductionSession(@PathVariable Long id) {
        return productionSessionService.getProductionSession(id);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductionSessionDto createProductionSession(@RequestBody @Valid ProductionSessionDetails productionSessionDetails) {
        return productionSessionService.createProductionSession(productionSessionDetails);
    }

    @Override
    @PostMapping("/{id}")
    public ProductionSessionDto createProductionSessionOrder(@PathVariable Long id, @RequestBody @Valid SessionOrderDetails sessionOrderDetails) {
        return productionSessionService.createProductionSessionOrder(id, sessionOrderDetails);
    }

    @Override
    @PutMapping("/{id}")
    public ProductionSessionDto updateProductionSession(@PathVariable Long id,
                                                        @RequestBody @Valid ProductionSessionDetails productionSessionDetails) {
        return productionSessionService.updateProductionSession(id, productionSessionDetails);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteProductionPlan(@PathVariable Long id) {
        productionSessionService.deleteProductionSession(id);
    }

}
