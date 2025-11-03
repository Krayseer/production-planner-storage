package ru.anykeyers.productionplannerstorage.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.anykeyers.productionplannerstorage.domain.dto.ProductDto;
import ru.anykeyers.productionplannerstorage.domain.mapper.ProductMapper;
import ru.anykeyers.productionplannerstorage.domain.request.ProductDetails;
import ru.anykeyers.productionplannerstorage.domain.Product;
import ru.anykeyers.productionplannerstorage.exception.ProductNotFoundException;
import ru.anykeyers.productionplannerstorage.repository.ProductRepository;
import ru.anykeyers.productionplannerstorage.service.ProductService;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    @Override
    public List<ProductDto> getAllProducts() {
        return productMapper.toDto(productRepository.findAll());
    }

    @Override
    public ProductDto getProduct(long productId) throws ProductNotFoundException {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
        return productMapper.toDto(product);
    }

    @Override
    public ProductDto createProduct(ProductDetails productDetails) {
        Product product = Product.builder()
                .name(productDetails.name())
                .assemblyProductivity(productDetails.assemblyProductivity())
                .active(productDetails.active())
                .build();
        Product savedProduct = productRepository.save(product);
        log.info("Created product {}", savedProduct);
        return productMapper.toDto(savedProduct);
    }

    @Override
    public ProductDto updateProduct(long productId, ProductDetails productDetails) throws ProductNotFoundException {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        existingProduct.setName(productDetails.name());
        existingProduct.setAssemblyProductivity(productDetails.assemblyProductivity());
        existingProduct.setActive(productDetails.active());
        Product updatedProduct = productRepository.save(existingProduct);
        log.info("Updated product {}", updatedProduct);
        return productMapper.toDto(updatedProduct);
    }

    @Override
    public void deleteProduct(long productId) {
        log.info("Deleting product with id {}", productId);
        productRepository.deleteById(productId);
    }

}
