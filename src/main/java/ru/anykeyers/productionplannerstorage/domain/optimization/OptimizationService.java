package ru.anykeyers.productionplannerstorage.domain.optimization;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.anykeyers.productionplannerstorage.domain.DtoMapper;
import ru.anykeyers.productionplannerstorage.domain.optimization.optimizer.Optimizer;
import ru.anykeyers.productionplannerstorage.domain.optimization.result.OptimizationResult;
import ru.anykeyers.productionplannerstorage.domain.optimization.result.OptimizationResultDto;
import ru.anykeyers.productionplannerstorage.domain.optimization.result.OptimizationResultRepository;
import ru.anykeyers.productionplannerstorage.domain.optimization.run.*;
import ru.anykeyers.productionplannerstorage.domain.session.ProductionSession;
import ru.anykeyers.productionplannerstorage.domain.session.ProductionSessionNotFoundException;
import ru.anykeyers.productionplannerstorage.domain.session.ProductionSessionRepository;

import java.util.List;

/**
 * Сервис параметров производительности
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OptimizationService {

    private final Optimizer optimizer;

    private final OptimizationRunRepository optimizationRunRepository;
    private final ProductionSessionRepository productionSessionRepository;
    private final OptimizationResultRepository optimizationResultRepository;

    private final DtoMapper<OptimizationRun, OptimizationRunDto> optimizationRunMapper;
    private final DtoMapper<OptimizationResult, OptimizationResultDto> optimizationResultMapper;

    /**
     * @return список активных параметров
     */
    @Transactional(readOnly = true)
    public List<OptimizationRunDto> getActiveOptimizationParameters() {
        return optimizationRunMapper.toDto(optimizationRunRepository.findAll());
    }

    /**
     * Создать параметр оптимизации
     *
     * @param optimizationRunDetails данные о параметре оптимизации
     */
    @Transactional
    public OptimizationRunDto createOptimizationRun(OptimizationRunDetails optimizationRunDetails) {
        OptimizationRun optimizationRun = new OptimizationRun();
        setDetailsToEntity(optimizationRun, optimizationRunDetails);
        OptimizationRun savedOptimizationRun = optimizationRunRepository.save(optimizationRun);
        log.info("Created optimization parameter: {}", savedOptimizationRun);
        return optimizationRunMapper.toDto(savedOptimizationRun);
    }

    /**
     * Запустить оптимизатор
     */
    @Transactional
    public List<OptimizationResultDto> optimize(Long optimizationRunId) {
        OptimizationRun optimizationRun = getOptimizationRun(optimizationRunId);
        List<OptimizationResult> optimizationResults = optimizer.optimize(optimizationRun);
        optimizationResultRepository.saveAll(optimizationResults);
        log.info("Save optimization results: {}", optimizationResults.size());
        return optimizationResultMapper.toDto(optimizationResults);
    }

    /**
     * Обновить параметр оптимизации
     *
     * @param optimizationParameterId       идентификатор параметра оптимизации
     * @param optimizationRunDetails  обновленные данные о параметре оптимизации
     */
    @Transactional
    public OptimizationRunDto updateOptimizationParameter(Long optimizationParameterId, OptimizationRunDetails optimizationRunDetails)
            throws OptimizationRunNotFoundException {
        OptimizationRun optimizationRun = getOptimizationRun(optimizationParameterId);
        setDetailsToEntity(optimizationRun, optimizationRunDetails);
        OptimizationRun savedOptimizationRun = optimizationRunRepository.save(optimizationRun);
        log.info("Updated optimization parameter: {}", savedOptimizationRun);
        return optimizationRunMapper.toDto(savedOptimizationRun);
    }

    private void setDetailsToEntity(OptimizationRun optimizationRun, OptimizationRunDetails optimizationRunDetails) {
        ProductionSession productionSession = productionSessionRepository.findById(optimizationRunDetails.productionSessionId())
                .orElseThrow(() -> new ProductionSessionNotFoundException(optimizationRunDetails.productionSessionId()));
        optimizationRun.setProductionSession(productionSession);
        optimizationRun.setRunTimestamp(optimizationRunDetails.runTimestamp());
        optimizationRun.setModelVersion(optimizationRunDetails.modelVersion());
        optimizationRun.setTardyDefaultK(optimizationRunDetails.kTardyDefault());
        optimizationRun.setUnderK(optimizationRunDetails.kUnder());
        optimizationRun.setOverK(optimizationRunDetails.kOver());
        optimizationRun.setAlpha(optimizationRunDetails.alpha());
        optimizationRun.setBeta(optimizationRunDetails.beta());
        optimizationRun.setDeltaBuffer(optimizationRunDetails.deltaBuffer());
        optimizationRun.setComment(optimizationRunDetails.comment());
    }

    private OptimizationRun getOptimizationRun(Long optimizationRunId) {
        return optimizationRunRepository.findById(optimizationRunId)
                .orElseThrow(() -> new OptimizationRunNotFoundException(optimizationRunId));
    }

}
