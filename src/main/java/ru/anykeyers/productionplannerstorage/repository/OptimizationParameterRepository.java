package ru.anykeyers.productionplannerstorage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anykeyers.productionplannerstorage.domain.OptimizationParameter;

import java.util.List;

/**
 * DAO параметров производительности
 */
public interface OptimizationParameterRepository extends JpaRepository<OptimizationParameter, Long> {

    /**
     * @return список всех активных параметров
     */
    List<OptimizationParameter> findAllByActiveIsTrue();

}
