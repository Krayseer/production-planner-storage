package ru.anykeyers.productionplannerstorage.domain.optimization;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.anykeyers.productionplannerstorage.domain.DtoMapper;
import ru.anykeyers.productionplannerstorage.domain.optimization.optimizer.Optimizer;
import ru.anykeyers.productionplannerstorage.domain.optimization.optimizer.OptimizerRequest;
import ru.anykeyers.productionplannerstorage.domain.optimization.optimizer.OptimizerResult;
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
    private final ProductionSessionRepository productionSessionRepository;
    private final OptimizationParameterRepository optimizationParameterRepository;
    private final DtoMapper<OptimizationRun, OptimizationRunDto> optimizationParameterMapper;

    /**
     * @return список активных параметров
     */
    public List<OptimizationRunDto> getActiveOptimizationParameters() {
        return optimizationParameterMapper.toDto(optimizationParameterRepository.findAllByActiveIsTrue());
    }

    /**
     * Создать параметр оптимизации
     *
     * @param optimizationRunDetails данные о параметре оптимизации
     */
    public OptimizationRunDto createOptimizationRun(OptimizationRunDetails optimizationRunDetails) {
        OptimizationRun optimizationRun = new OptimizationRun();
        setDetailsToEntity(optimizationRun, optimizationRunDetails);
        OptimizationRun savedOptimizationRun = optimizationParameterRepository.save(optimizationRun);
        log.info("Created optimization parameter: {}", savedOptimizationRun);
        return optimizationParameterMapper.toDto(savedOptimizationRun);
    }

    /**
     * Запустить оптимизатор
     */
    public OptimizerResult optimize(OptimizerRequest optimizerRequest) {
        OptimizerResult optimizerResult = optimizer.optimize(optimizerRequest);
    }

    /**
     * Обновить параметр оптимизации
     *
     * @param optimizationParameterId       идентификатор параметра оптимизации
     * @param optimizationRunDetails  обновленные данные о параметре оптимизации
     */
    public OptimizationRunDto updateOptimizationParameter(Long optimizationParameterId, OptimizationRunDetails optimizationRunDetails)
            throws OptimizationParameterNotFoundException {
        OptimizationRun optimizationRun = optimizationParameterRepository.findById(optimizationParameterId)
                .orElseThrow(() -> new OptimizationParameterNotFoundException(optimizationParameterId));
        setDetailsToEntity(optimizationRun, optimizationRunDetails);
        OptimizationRun savedOptimizationRun = optimizationParameterRepository.save(optimizationRun);
        log.info("Updated optimization parameter: {}", savedOptimizationRun);
        return optimizationParameterMapper.toDto(savedOptimizationRun);
    }

    private void setDetailsToEntity(OptimizationRun optimizationRun, OptimizationRunDetails optimizationRunDetails) {
        ProductionSession productionSession = productionSessionRepository.findById(optimizationRunDetails.productionSessionId())
                .orElseThrow(() -> new ProductionSessionNotFoundException(optimizationRunDetails.productionSessionId()));
        optimizationRun.setProductionSession(productionSession);
        optimizationRun.setRunTimestamp(optimizationRunDetails.runTimestamp());
        optimizationRun.setModelVersion(optimizationRunDetails.modelVersion());
        optimizationRun.setKTardyDefault(optimizationRunDetails.kTardyDefault());
        optimizationRun.setKUnder(optimizationRunDetails.kUnder());
        optimizationRun.setAlpha(optimizationRunDetails.alpha());
        optimizationRun.setBeta(optimizationRunDetails.beta());
        optimizationRun.setDeltaBuffer(optimizationRunDetails.deltaBuffer());
        optimizationRun.setComment(optimizationRunDetails.comment());
    }

}
