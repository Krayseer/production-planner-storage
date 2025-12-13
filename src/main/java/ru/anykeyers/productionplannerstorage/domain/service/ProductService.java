package ru.anykeyers.productionplannerstorage.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.anykeyers.productionplannerstorage.domain.DtoMapper;
import ru.anykeyers.productionplannerstorage.domain.dto.ProductDto;
import ru.anykeyers.productionplannerstorage.domain.dto.request.ProductDetails;
import ru.anykeyers.productionplannerstorage.domain.model.Product;
import ru.anykeyers.productionplannerstorage.exception.ProductNotFoundException;
import ru.anykeyers.productionplannerstorage.infrastructure.database.ProductRepository;

import java.util.List;

/**
 * Сервис изделий
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final DtoMapper<Product, ProductDto> productMapper;

    /**
     * @return список всех изделий
     */
    public List<ProductDto> getAllProducts() {
        return productMapper.toDto(productRepository.findAll());
    }

    /**
     * Получить продукт
     *
     * @param productId идентификатор продукта
     */
    public ProductDto getProduct(long productId) throws ProductNotFoundException {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
        return productMapper.toDto(product);
    }

    /**
     * Создать продукт
     *
     * @param productDetails данные о продукте
     * @return созданный продукт
     */
    public ProductDto createProduct(ProductDetails productDetails) {
        Product product = Product.builder()
                .name(productDetails.name())
                .active(productDetails.active())
                .build();
        Product savedProduct = productRepository.save(product);
        log.info("Created product {}", savedProduct);
        return productMapper.toDto(savedProduct);
    }

    /**
     * Обновить продукт
     *
     * @param productId         идентификатор продукта
     * @param productDetails    обновленные данные о продукте
     * @return обновленный продукт
     */
    public ProductDto updateProduct(long productId, ProductDetails productDetails) throws ProductNotFoundException {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        existingProduct.setName(productDetails.name());
        existingProduct.setActive(productDetails.active());
        Product updatedProduct = productRepository.save(existingProduct);
        log.info("Updated product {}", updatedProduct);
        return productMapper.toDto(updatedProduct);
    }

    /**
     * Удалить продукт
     *
     * @param productId идентификатор продукта
     */
    public void deleteProduct(long productId) {
        log.info("Deleting product with id {}", productId);
        productRepository.deleteById(productId);
    }

}
