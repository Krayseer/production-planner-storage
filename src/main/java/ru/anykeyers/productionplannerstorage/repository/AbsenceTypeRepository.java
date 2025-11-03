package ru.anykeyers.productionplannerstorage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anykeyers.productionplannerstorage.domain.AbsenceType;

/**
 * DAO типов отсутствий
 */
public interface AbsenceTypeRepository extends JpaRepository<AbsenceType, Long> {

    /**
     * Существует ли тип отсутствия по указанному коду
     *
     * @param code код отсутствия
     */
    boolean existsAbsenceTypeByCode(String code);

}
