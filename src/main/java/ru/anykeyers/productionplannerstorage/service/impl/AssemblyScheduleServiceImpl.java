package ru.anykeyers.productionplannerstorage.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.anykeyers.productionplannerstorage.domain.AssemblySchedule;
import ru.anykeyers.productionplannerstorage.domain.Product;
import ru.anykeyers.productionplannerstorage.domain.Team;
import ru.anykeyers.productionplannerstorage.domain.dto.AssemblyScheduleDto;
import ru.anykeyers.productionplannerstorage.domain.mapper.AssemblyScheduleMapper;
import ru.anykeyers.productionplannerstorage.domain.request.AssemblyScheduleDetails;
import ru.anykeyers.productionplannerstorage.exception.AssemblyScheduleNotFoundException;
import ru.anykeyers.productionplannerstorage.exception.ProductNotFoundException;
import ru.anykeyers.productionplannerstorage.exception.TeamNotFoundException;
import ru.anykeyers.productionplannerstorage.repository.AssemblyScheduleRepository;
import ru.anykeyers.productionplannerstorage.repository.ProductRepository;
import ru.anykeyers.productionplannerstorage.repository.TeamRepository;
import ru.anykeyers.productionplannerstorage.service.AssemblyScheduleService;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AssemblyScheduleServiceImpl implements AssemblyScheduleService {

    private final TeamRepository teamRepository;
    private final ProductRepository productRepository;
    private final AssemblyScheduleMapper assemblyScheduleMapper;
    private final AssemblyScheduleRepository assemblyScheduleRepository;

    @Override
    public List<AssemblyScheduleDto> getAssemblySchedules() {
        return assemblyScheduleMapper.toDto(assemblyScheduleRepository.findAll());
    }

    @Override
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

    @Override
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
