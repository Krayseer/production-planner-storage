package ru.anykeyers.productionplannerstorage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anykeyers.productionplannerstorage.domain.Product;

/**
 * DAO продуктов
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}
