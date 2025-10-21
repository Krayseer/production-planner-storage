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

}
