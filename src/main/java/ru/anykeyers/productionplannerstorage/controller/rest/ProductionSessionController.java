package ru.anykeyers.productionplannerstorage.controller.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.anykeyers.productionplannerstorage.controller.ProductionSessionApi;
import ru.anykeyers.productionplannerstorage.domain.dto.ProductionSessionDto;
import ru.anykeyers.productionplannerstorage.domain.request.ProductionSessionDetails;
import ru.anykeyers.productionplannerstorage.domain.request.SessionOrderDetails;
import ru.anykeyers.productionplannerstorage.service.ProductionSessionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(RestControllerPath.PRODUCTION_SESSIONS)
public class ProductionSessionController implements ProductionSessionApi {

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
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ProductionSessionDto updateProductionSession(@PathVariable Long id,
                                                        @RequestBody @Valid ProductionSessionDetails productionSessionDetails) {
        return productionSessionService.updateProductionSession(id, productionSessionDetails);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductionPlan(@PathVariable Long id) {
        productionSessionService.deleteProductionSession(id);
    }

}
