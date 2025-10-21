package ru.anykeyers.productionplannerstorage.config;

import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * Конфигурация OpenAPI
 */
@Configuration
public class OpenApiConfig {

    /**
     * Переопределение порядка сортировки методов
     */
    @Bean
    public OpenApiCustomizer sortOperations() {
        return openApi -> {
            Paths sortedPaths = new Paths();
            openApi.getPaths().entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .forEachOrdered(e -> {
                        var pathItem = e.getValue();
                        var newPathItem = new PathItem();
                        if (pathItem.getGet() != null) {
                            newPathItem.setGet(pathItem.getGet());
                        }
                        if (pathItem.getPost() != null) {
                            newPathItem.setPost(pathItem.getPost());
                        }
                        if (pathItem.getPut() != null) {
                            newPathItem.setPut(pathItem.getPut());
                        }
                        if (pathItem.getDelete() != null) {
                            newPathItem.setDelete(pathItem.getDelete());
                        }
                        sortedPaths.addPathItem(e.getKey(), newPathItem);
                    });
            openApi.setPaths(sortedPaths);
        };
    }

}
