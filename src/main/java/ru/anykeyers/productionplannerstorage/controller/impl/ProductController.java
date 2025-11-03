package ru.anykeyers.productionplannerstorage.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.anykeyers.productionplannerstorage.controller.ProductApi;
import ru.anykeyers.productionplannerstorage.domain.dto.ProductDto;
import ru.anykeyers.productionplannerstorage.domain.request.ProductDetails;
import ru.anykeyers.productionplannerstorage.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController implements ProductApi {

    private final ProductService productService;

    @Override
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @Override
    public ProductDto getProductById(long id) {
        return productService.getProduct(id);
    }

    @Override
    public ProductDto createProduct(ProductDetails productDetails) {
        return productService.createProduct(productDetails);
    }

    @Override
    public ProductDto updateProduct(long id, ProductDetails productDetails) {
        return productService.updateProduct(id, productDetails);
    }

    @Override
    public void deleteProduct(long id) {
        productService.deleteProduct(id);
    }

}
