package com.rtarcisio.gatewayms.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routes (RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route(r -> r.path("/usuario/**")
                        .uri("lb://usuarios-ms"))

                .route(r -> r.path("/auth/**")
                        .uri("lb://usuarios-ms"))

                .route(r -> r.path("/sport/**")
                        .uri("lb://olympic-ms"))
                .route(r -> r.path("/country/**")
                        .uri("lb://olympic-ms"))
                .route(r -> r.path("/medal/**")
                        .uri("lb://olympic-ms"))
                .build();
    }
}
