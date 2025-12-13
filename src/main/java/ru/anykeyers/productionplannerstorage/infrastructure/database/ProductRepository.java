package ru.anykeyers.productionplannerstorage.infrastructure.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.anykeyers.productionplannerstorage.domain.model.Product;

/**
 * DAO продуктов
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
