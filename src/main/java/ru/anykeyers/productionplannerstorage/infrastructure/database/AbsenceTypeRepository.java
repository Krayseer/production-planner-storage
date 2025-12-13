package ru.anykeyers.productionplannerstorage.infrastructure.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.anykeyers.productionplannerstorage.domain.model.AbsenceType;

/**
 * DAO типов отсутствий
 */
@Repository
public interface AbsenceTypeRepository extends JpaRepository<AbsenceType, Long> {

    /**
     * Существует ли тип отсутствия по указанному коду
     *
     * @param code код отсутствия
     */
    boolean existsAbsenceTypeByCode(String code);

}
