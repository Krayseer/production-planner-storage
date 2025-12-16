package ru.anykeyers.productionplannerstorage.domain.absencetype;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import ru.anykeyers.productionplannerstorage.domain.workshedule.WorkSchedule;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <b>Тип отсутствия</b>
 * <p/>
 * Справочник причин отсутствия на работе
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AbsenceType {
    /**
     * Стандартный флаг активности
     */
    private static final Boolean DEFAULT_ACTIVE = Boolean.TRUE;
    /**
     * Стандартное влияние на производительность
     */
    private static BigDecimal DEFAULT_IMPACT_FACTOR = BigDecimal.ZERO;

    /**
     * Уникальный идентификатор типа
     */
    private Long id;
    /**
     * Код отсутствия
     * <ul>
     *     <li>'О' - отпуск</li>
     *     <li>'Б' - больничный</li>
     *     <li>'А' - без содержания</li>
     * </ul>
     */
    private String code;
    /**
     * Название типа
     */
    private String name;
    /**
     * Влияние на производительность (0.0 - 1.0)
     */
    @Builder.Default
    private BigDecimal impactFactor = DEFAULT_IMPACT_FACTOR;
    /**
     * Флаг активности
     */
    @Builder.Default
    private Boolean active = DEFAULT_ACTIVE;
    /**
     * Дата создания
     */
    @CreationTimestamp
    private LocalDateTime createdAt;

    private List<WorkSchedule> workSchedules;
}
