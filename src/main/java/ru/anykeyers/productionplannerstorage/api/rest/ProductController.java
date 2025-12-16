package ru.anykeyers.productionplannerstorage.api.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.anykeyers.productionplannerstorage.api.ProductApi;
import ru.anykeyers.productionplannerstorage.domain.product.ProductDto;
import ru.anykeyers.productionplannerstorage.domain.product.ProductDetails;
import ru.anykeyers.productionplannerstorage.domain.product.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(RestControllerPath.PRODUCTS)
class ProductController implements ProductApi {

    private final ProductService productService;

    @Override
    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @Override
    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable long id) {
        return productService.getProduct(id);
    }

    @Override
    @PostMapping
    public ProductDto createProduct(@RequestBody @Valid ProductDetails productDetails) {
        return productService.createProduct(productDetails);
    }

    @Override
    @PutMapping("/{id}")
    public ProductDto updateProduct(@PathVariable long id, @RequestBody @Valid ProductDetails productDetails) {
        return productService.updateProduct(id, productDetails);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
    }

}
