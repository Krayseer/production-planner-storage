package ru.anykeyers.productionplannerstorage.controller;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Пути контроллеров
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class ControllerPath {

    public static final String BASE = "/api";
    public static final String PRODUCTS = BASE + "/products";
    public static final String PRODUCTION_PLANS = BASE + "/production-plans";
    public static final String OPTIMIZATION_PARAMETERS = BASE + "/optimization-parameters";

}
