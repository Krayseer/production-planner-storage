package ru.anykeyers.productionplannerstorage.domain.optimization.optimizer;

import ru.anykeyers.productionplannerstorage.domain.optimization.OptimizationRun;
import ru.anykeyers.productionplannerstorage.domain.team.productivity.TeamProductivity;
import ru.anykeyers.productionplannerstorage.domain.session.order.SessionOrderDto;

import java.time.LocalDate;
import java.util.List;

public record OptimizerRequest(Long sessionId,
                               LocalDate startDate,
                               Integer numDays,
                               List<SessionOrderDto> orders,
                               TeamProductivity teamProductivity,
                               OptimizationRun params) {
}
