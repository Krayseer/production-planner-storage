package ru.anykeyers.productionplannerstorage.domain.optimization.optimizer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.anykeyers.productionplannerstorage.config.WebConfig;
import ru.anykeyers.productionplannerstorage.domain.DtoMapper;
import ru.anykeyers.productionplannerstorage.domain.ProductionType;
import ru.anykeyers.productionplannerstorage.domain.optimization.result.OptimizationResult;
import ru.anykeyers.productionplannerstorage.domain.optimization.run.OptimizationRun;
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
import ru.anykeyers.productionplannerstorage.domain.team.productivity.TeamProductivityRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Slf4j
@Component
@RequiredArgsConstructor
class OptimizerProxy implements Optimizer {

    private final RestTemplate restTemplate;
    private final TeamRepository teamRepository;
    private final ProductRepository productRepository;
    private final SessionOrderRepository sessionOrderRepository;
    private final OptimizationRunRepository optimizationRunRepository;
    private final TeamProductivityRepository teamProductivityRepository;

    @Qualifier("optimizerProxy.ProxyMapper")
    private final ProxyMapper proxyMapper;

    @Value("${OPTIMIZER_SERVICE_PATH:http://localhost:8000}")
    private String optimizerUrl;

    @Override
    @SneakyThrows
    public List<OptimizationResult> optimize(OptimizationRun optimizationRun, Map<Long, Integer> absenceCountByTeam) {
        LocalDate startDate = optimizationRun.getRunTimestamp();
        ProductionSession productionSession = optimizationRun.getProductionSession();
        long numDays = ChronoUnit.DAYS.between(startDate, productionSession.getEndDate());
        OptimizerRequest request = new OptimizerRequest(
                productionSession.getId(),
                startDate.toString(),
                numDays,
                optimizationRun.getProductionSession().getSessionOrders().stream().map(this::toOrderRequest).toList(),
                toDto(teamProductivityRepository.findAll(), absenceCountByTeam),
                proxyMapper.toDto(optimizationRun)
        );
        log.info("Optimizer request: {}", request);
        log.info("Optimizer request as JSON: {}", WebConfig.createObjectMapper().writeValueAsString(request));
        String path = optimizerUrl + "/api/v1/optimize/" + optimizationRun.getModelVersion();
        OptimizerResponse optimizerResponse = restTemplate.postForObject(path, request, OptimizerResponse.class);
        if (optimizerResponse == null) {
            return null;
        }
        List<OptimizationResult> optimizationResults = new ArrayList<>();
        optimizationRun.setObjectiveValue(optimizerResponse.objectiveValue());
        optimizerResponse.teamDayPlans().forEach(teamDayPlan -> {
            Team team = teamRepository.findById(teamDayPlan.teamId())
                    .orElseThrow(() -> new TeamNotFoundException(teamDayPlan.teamId()));
            for (OptimizerResponse.Team.Task task: teamDayPlan.tasks()) {
                if (task.dayIndex() != 1) {
                    continue;
                }
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
                            int quantity = sessionOrder.getQuantityFact() == null ? sessionOrder.getQuantity() : sessionOrder.getQuantityFact();
                            sessionOrder.setQuantityFact(quantity - task.plannedQuantity().intValue());
                            log.info("Update session order: {}", sessionOrder);
                            sessionOrderRepository.save(sessionOrder);
                        });
            }
        });
        optimizationRunRepository.save(optimizationRun);
        log.info("Updated optimization run: {}", optimizationRun);
        return optimizationResults;
    }

    private List<TeamProductivityForProxy> toDto(List<TeamProductivity> teamProductivityList, Map<Long, Integer> absenceCountByTeam) {
        return teamProductivityList.stream()
                .map(t -> {
                    Team team = t.getTeam();
                    double employeeCount = team.getEmployees().size();
                    double absenceCount = absenceCountByTeam.getOrDefault(team.getId(), 0);
                    BigDecimal productivity = absenceCount == 0
                            ? t.getProductivity()
                            : t.getProductivity().multiply(new BigDecimal(employeeCount - (absenceCount/employeeCount)));
                    return new TeamProductivityForProxy(
                            t.getTeam().getId(),
                            t.getProduct().getId(),
                            t.getProductionType().name().toLowerCase(),
                            productivity);
                }).toList();
    }

    private OptimizerRequest.Order toOrderRequest(SessionOrder order) {
        return new OptimizerRequest.Order(
                order.getId(),
                order.getProduct().getId(),
                order.getProductionType().name().toLowerCase(),
                order.getQuantity(),
                order.getDeadlineDate().toString()
        );
    }

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record OptimizerRequest(Long sessionId,
                                   String startDate,
                                   long numDays,
                                   List<Order> orders,
                                   List<TeamProductivityForProxy> teamProductivity,
                                   OptimizationRunForProxy params) {
        @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
        public record Order(Long orderId,
                            Long productId,
                            String productionType,
                            Integer quantity,
                            String deadlineDate) {
        }
    }

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record OptimizerResponse(double objectiveValue, List<Team> teamDayPlans, Map<String, Object> orderTardiness) {
        public record Team(Long teamId,
                           int dayIndex,
                           LocalDate workDate,
                           List<Task> tasks) {
            @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
            public record Task(int dayIndex,
                               LocalDate workDate,
                               Long productId,
                               String productionType,
                               BigDecimal plannedHours,
                               BigDecimal plannedQuantity) {
            }
        }
    }

    public record OptimizationRunForProxy(
            @JsonProperty("k_tardy_default")
            BigDecimal tardyDefaultK,
            @JsonProperty("k_under")
            BigDecimal underK,
            @JsonProperty("k_over")
            BigDecimal overK,
            BigDecimal alpha,
            BigDecimal beta
    ) {}

    public record TeamProductivityForProxy(
            Long teamId,
            Long productId,
            String productionType,
            BigDecimal productivity
    ) {}

    @Mapper(componentModel = SPRING)
    public interface ProxyMapper extends DtoMapper<OptimizationRun, OptimizationRunForProxy> {
    }

}
