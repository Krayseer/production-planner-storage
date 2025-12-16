package ru.anykeyers.productionplannerstorage.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DAO продуктов
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
