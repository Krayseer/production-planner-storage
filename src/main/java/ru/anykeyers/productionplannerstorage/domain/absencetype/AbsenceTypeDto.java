package ru.anykeyers.productionplannerstorage.domain.absencetype;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AbsenceTypeDto(
        Long id,
        String code,
        String name,
        BigDecimal impactFactor,
        Boolean active,
        LocalDateTime createdAt
) {}
