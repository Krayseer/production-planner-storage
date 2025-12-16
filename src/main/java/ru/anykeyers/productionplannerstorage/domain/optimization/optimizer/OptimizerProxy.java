package ru.anykeyers.productionplannerstorage.domain.optimization.optimizer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Component
public class OptimizerProxy implements Optimizer {

    private final RestTemplate restTemplate;

    @Value("${OPTIMIZER_SERVICE_PATH}")
    private String optimizerUrl;

    public OptimizerProxy(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public OptimizerResult optimize(OptimizerRequest optimizerRequest) {
        try {
            return restTemplate.postForObject(new URI(optimizerUrl), optimizerRequest, OptimizerResult.class);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
