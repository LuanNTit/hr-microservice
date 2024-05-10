package com.example.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@SpringBootApplication
@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("authentication-service-route", r -> r.path("/auth/**")
                        .uri("http://localhost:8080/api/users"))
                .route("employee-service-route", r -> r.path("/employee/**")
                        .uri("http://localhost:8081/api/employees"))
                .route("user-service-route", r -> r.path("/user/**")
                        .uri("http://localhost:8082"))
                .route("default-route", r -> r.path("/**")
                        .uri("http://localhost:8080/api/users"))
                .build();
    }
//    @Bean
//    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route("example_route", r -> r.path("/example")
//                        .uri("http://example.com/abc"))
//                .route("employee-service-route", r -> r.path("/employee/**")
//                        .uri("http://localhost:8081/api/employees"))
//                .build();
//    }
}
