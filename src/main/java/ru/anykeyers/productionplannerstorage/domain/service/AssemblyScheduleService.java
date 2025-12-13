package ru.anykeyers.productionplannerstorage.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.anykeyers.productionplannerstorage.domain.DtoMapper;
import ru.anykeyers.productionplannerstorage.domain.model.AssemblySchedule;
import ru.anykeyers.productionplannerstorage.domain.model.Product;
import ru.anykeyers.productionplannerstorage.domain.model.Team;
import ru.anykeyers.productionplannerstorage.domain.dto.AssemblyScheduleDto;
import ru.anykeyers.productionplannerstorage.domain.dto.request.AssemblyScheduleDetails;
import ru.anykeyers.productionplannerstorage.exception.AssemblyScheduleNotFoundException;
import ru.anykeyers.productionplannerstorage.exception.ProductNotFoundException;
import ru.anykeyers.productionplannerstorage.exception.TeamNotFoundException;
import ru.anykeyers.productionplannerstorage.infrastructure.database.AssemblyScheduleRepository;
import ru.anykeyers.productionplannerstorage.infrastructure.database.ProductRepository;
import ru.anykeyers.productionplannerstorage.infrastructure.database.TeamRepository;

import java.util.List;

/**
 * Сервис графиков слесарных участков
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AssemblyScheduleService {

    private final TeamRepository teamRepository;
    private final ProductRepository productRepository;
    private final AssemblyScheduleRepository assemblyScheduleRepository;
    private final DtoMapper<AssemblySchedule, AssemblyScheduleDto> assemblyScheduleMapper;

    /**
     * @return список графиков слесарных участков
     */
    public List<AssemblyScheduleDto> getAssemblySchedules() {
        return assemblyScheduleMapper.toDto(assemblyScheduleRepository.findAll());
    }

    /**
     * Создать график слесарного участка
     *
     * @param assemblyScheduleDetails данные для создания графика слесарного участка
     */
    public AssemblyScheduleDto createAssemblySchedule(AssemblyScheduleDetails assemblyScheduleDetails)
            throws TeamNotFoundException, ProductNotFoundException {
        AssemblySchedule assemblySchedule = assemblyScheduleRepository.save(AssemblySchedule.builder()
                .team(getTeam(assemblyScheduleDetails.teamId()))
                .date(assemblyScheduleDetails.date())
                .product(getProduct(assemblyScheduleDetails.productId()))
                .assemblyHours(assemblyScheduleDetails.assemblyHours())
                .plannedQuantity(assemblyScheduleDetails.plannedQuantity())
                .build());
        log.info("Created assembly schedule: {}", assemblySchedule);
        return assemblyScheduleMapper.toDto(assemblySchedule);
    }

    /**
     * Обновить данные о графике слесарного участка
     *
     * @param assemblyScheduleId        идентификатор графика слесарного участка
     * @param assemblyScheduleDetails   обновленные данные о графике слесарного участка
     */
    public AssemblyScheduleDto updateAssemblySchedule(Long assemblyScheduleId, AssemblyScheduleDetails assemblyScheduleDetails)
            throws AssemblyScheduleNotFoundException, TeamNotFoundException, ProductNotFoundException {
        AssemblySchedule assemblySchedule = assemblyScheduleRepository.findById(assemblyScheduleId)
                .orElseThrow(() -> new AssemblyScheduleNotFoundException(assemblyScheduleId));
        assemblySchedule.setTeam(getTeam(assemblyScheduleDetails.teamId()));
        assemblySchedule.setDate(assemblyScheduleDetails.date());
        assemblySchedule.setProduct(getProduct(assemblyScheduleDetails.productId()));
        assemblySchedule.setAssemblyHours(assemblyScheduleDetails.assemblyHours());
        assemblySchedule.setPlannedQuantity(assemblyScheduleDetails.plannedQuantity());
        AssemblySchedule updatedAssemblySchedule = assemblyScheduleRepository.save(assemblySchedule);
        log.info("Updated assembly schedule: {}", updatedAssemblySchedule);
        return assemblyScheduleMapper.toDto(updatedAssemblySchedule);
    }

    private Team getTeam(Long teamId) {
        return teamRepository.findById(teamId).orElseThrow(() -> new TeamNotFoundException(teamId));
    }

    private Product getProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
    }

}
