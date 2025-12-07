package ru.anykeyers.productionplannerstorage.controller.rest;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * REST пути контроллеров
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class RestControllerPath {

    public static final String BASE = "/api/v1";
    public static final String TEAMS = BASE + "/teams";
    public static final String PRODUCTS = BASE + "/products";
    public static final String EMPLOYEES = BASE + "/employees";
    public static final String ABSENCE_TYPES = BASE + "/absence-types";
    public static final String WORK_SCHEDULES = BASE + "/work-schedules";
    public static final String PRODUCTION_SESSIONS = BASE + "/sessions";
    public static final String TEAM_PRODUCTIVITY = BASE + "/team-productivity";
    public static final String ASSEMBLY_SCHEDULES = BASE + "/assembly-schedule";
    public static final String OPTIMIZATION_PARAMETERS = BASE + "/optimization-parameters";

}
