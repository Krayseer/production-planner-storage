package ru.anykeyers.productionplannerstorage.domain.optimization.optimizer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.anykeyers.productionplannerstorage.domain.DtoMapper;
import ru.anykeyers.productionplannerstorage.domain.ProductionType;
import ru.anykeyers.productionplannerstorage.domain.optimization.result.OptimizationResult;
import ru.anykeyers.productionplannerstorage.domain.optimization.run.OptimizationRun;
import ru.anykeyers.productionplannerstorage.domain.optimization.run.OptimizationRunDto;
import ru.anykeyers.productionplannerstorage.domain.optimization.run.OptimizationRunRepository;
import ru.anykeyers.productionplannerstorage.domain.product.Product;
import ru.anykeyers.productionplannerstorage.domain.product.ProductNotFoundException;
import ru.anykeyers.productionplannerstorage.domain.product.ProductRepository;
import ru.anykeyers.productionplannerstorage.domain.session.ProductionSession;
import ru.anykeyers.productionplannerstorage.domain.session.order.SessionOrder;
import ru.anykeyers.productionplannerstorage.domain.session.order.SessionOrderRepository;
import ru.anykeyers.productionplannerstorage.domain.team.Team;
import ru.anykeyers.productionplannerstorage.domain.team.TeamNotFoundException;
import ru.anykeyers.productionplannerstorage.domain.team.TeamRepository;
import ru.anykeyers.productionplannerstorage.domain.team.productivity.TeamProductivity;
import ru.anykeyers.productionplannerstorage.domain.team.productivity.TeamProductivityDto;
import ru.anykeyers.productionplannerstorage.domain.team.productivity.TeamProductivityRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
class OptimizerProxy implements Optimizer {

    private final RestTemplate restTemplate;
    private final TeamRepository teamRepository;
    private final ProductRepository productRepository;
    private final SessionOrderRepository sessionOrderRepository;
    private final OptimizationRunRepository optimizationRunRepository;
    private final TeamProductivityRepository teamProductivityRepository;

    private final DtoMapper<OptimizationRun, OptimizationRunDto> optimizationRunMapper;
    private final DtoMapper<TeamProductivity, TeamProductivityDto> teamProductivityMapper;

    @Value("${OPTIMIZER_SERVICE_PATH:localhost:8000}")
    private String optimizerUrl;

    public OptimizerProxy(RestTemplateBuilder restTemplateBuilder,
                          TeamRepository teamRepository,
                          OptimizationRunRepository optimizationRunRepository,
                          TeamProductivityRepository teamProductivityRepository,
                          DtoMapper<OptimizationRun, OptimizationRunDto> optimizationRunMapper,
                          DtoMapper<TeamProductivity, TeamProductivityDto> teamProductivityMapper,
                          ProductRepository productRepository,
                          SessionOrderRepository sessionOrderRepository) {
        this.restTemplate = restTemplateBuilder.build();
        this.teamRepository = teamRepository;
        this.productRepository = productRepository;
        this.optimizationRunMapper = optimizationRunMapper;
        this.sessionOrderRepository = sessionOrderRepository;
        this.teamProductivityMapper = teamProductivityMapper;
        this.optimizationRunRepository = optimizationRunRepository;
        this.teamProductivityRepository = teamProductivityRepository;
    }

    @Override
    public List<OptimizationResult> optimize(OptimizationRun optimizationRun) {
        LocalDate startDate = optimizationRun.getRunTimestamp();
        ProductionSession productionSession = optimizationRun.getProductionSession();
        long numDays = ChronoUnit.DAYS.between(productionSession.getEndDate(), startDate);
        OptimizerRequest request = new OptimizerRequest(
                productionSession.getId(),
                startDate,
                numDays,
                optimizationRun.getProductionSession().getSessionOrders().stream().map(this::toOrderRequest).toList(),
                teamProductivityMapper.toDto(teamProductivityRepository.findAll()),
                optimizationRunMapper.toDto(optimizationRun)
        );
        OptimizerResponse optimizerResponse = restTemplate.postForObject(optimizerUrl, request, OptimizerResponse.class);
        if (optimizerResponse == null) {
            return null;
        }
        List<OptimizationResult> optimizationResults = new ArrayList<>();
        optimizationRun.setObjectiveValue(optimizerResponse.objectiveValue());
        optimizerResponse.teamDayPlans().forEach(teamDayPlan -> {
            Team team = teamRepository.findById(teamDayPlan.teamId())
                    .orElseThrow(() -> new TeamNotFoundException(teamDayPlan.teamId()));
            teamDayPlan.tasks().forEach(task -> {
                Product product = productRepository.findById(task.productId())
                        .orElseThrow(() -> new ProductNotFoundException(task.productId()));
                OptimizationResult optimizationResult = new OptimizationResult();
                optimizationResult.setDayIndex(task.dayIndex());
                optimizationResult.setWorkDate(task.workDate());
                optimizationResult.setProductionType(ProductionType.valueOf(task.productionType().toUpperCase()));
                optimizationResult.setPlannedHours(task.plannedHours());
                optimizationResult.setPlannedQuantity(task.plannedQuantity());
                optimizationResult.setOptimizationRun(optimizationRun);
                optimizationResult.setProductionSession(productionSession);
                optimizationResult.setTeam(team);
                optimizationResult.setProduct(product);
                optimizationResults.add(optimizationResult);
                log.info("Created optimization result: {}", optimizationResult);
                sessionOrderRepository.findByProduct(product)
                        .ifPresent(sessionOrder -> {
                            sessionOrder.setQuantityFact(sessionOrder.getQuantityFact() - task.plannedQuantity().intValue());
                            log.info("Update session order: {}", sessionOrder);
                            sessionOrderRepository.save(sessionOrder);
                        });
            });
        });
        optimizationRunRepository.save(optimizationRun);
        log.info("Updated optimization run: {}", optimizationRun);
        return optimizationResults;
    }

    private OptimizerRequest.Order toOrderRequest(SessionOrder order) {
        return new OptimizerRequest.Order(
                order.getId(),
                order.getProduct().getId(),
                order.getProductionType(),
                order.getQuantityFact(),
                order.getDeadlineDate()
        );
    }

    public record OptimizerRequest(Long sessionId,
                                   LocalDate startDate,
                                   long numDays,
                                   List<Order> orders,
                                   List<TeamProductivityDto> teamProductivity,
                                   OptimizationRunDto params) {
        public record Order(Long orderId,
                            Long productId,
                            ProductionType productionType,
                            Integer quantity,
                            LocalDate deadlineDate) {
        }
    }

    public record OptimizerResponse(double objectiveValue, List<Team> teamDayPlans) {
        public record Team(Long teamId,
                           int dayIndex,
                           LocalDate workDate,
                           List<Task> tasks) {
            public record Task(int dayIndex,
                               LocalDate workDate,
                               Long productId,
                               String productionType,
                               BigDecimal plannedHours,
                               BigDecimal plannedQuantity) {
            }
        }
    }

}
