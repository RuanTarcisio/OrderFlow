package com.rtarcisio.gatewayms.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routes (RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route(r -> r.path("/inventory/**")
                        .uri("lb://inventory-ms"))

                .route(r -> r.path("/product/**")
                        .uri("lb://inventory-ms"))

                .route(r -> r.path("/orders/**")
                        .uri("lb://order-ms"))

//                .route(p -> p
//                        .path("/rtarcisio/accounts/**")
//                        .filters( f -> f.rewritePath("/eazybank/accounts/(?<segment>.*)","/${segment}")
//                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
//                        .uri("lb://ACCOUNTS"))
                .build();
    }
}
