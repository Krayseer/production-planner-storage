package ru.anykeyers.productionplannerstorage.service;

import ru.anykeyers.productionplannerstorage.domain.dto.AssemblyScheduleDto;
import ru.anykeyers.productionplannerstorage.domain.request.AssemblyScheduleDetails;
import ru.anykeyers.productionplannerstorage.exception.AssemblyScheduleNotFoundException;
import ru.anykeyers.productionplannerstorage.exception.ProductNotFoundException;
import ru.anykeyers.productionplannerstorage.exception.TeamNotFoundException;

import java.util.List;

/**
 * Сервис графиков слесарных участков
 */
public interface AssemblyScheduleService {

    /**
     * @return список графиков слесарных участков
     */
    List<AssemblyScheduleDto> getAssemblySchedules();

    /**
     * Создать график слесарного участка
     *
     * @param assemblyScheduleDetails данные для создания графика слесарного участка
     */
    AssemblyScheduleDto createAssemblySchedule(AssemblyScheduleDetails assemblyScheduleDetails)
            throws TeamNotFoundException, ProductNotFoundException;

    /**
     * Обновить данные о графике слесарного участка
     *
     * @param assemblyScheduleId        идентификатор графика слесарного участка
     * @param assemblyScheduleDetails   обновленные данные о графике слесарного участка
     */
    AssemblyScheduleDto updateAssemblySchedule(Long assemblyScheduleId, AssemblyScheduleDetails assemblyScheduleDetails)
            throws AssemblyScheduleNotFoundException, TeamNotFoundException, ProductNotFoundException;

}
