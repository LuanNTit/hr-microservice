package com.example.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class ApigatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApigatewayApplication.class, args);
	}
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("authentication-service-route", r -> r.path("/auth/**")
						.uri("http://localhost:8080"))
				.route("employee-service-route", r -> r.path("/employee/**")
						.uri("http://localhost:8081"))
				.route("user-service-route", r -> r.path("/user/**")
						.uri("http://localhost:8082"))
				.route("default-route", r -> r.path("/**")
						.uri("http://localhost:8080"))
				.build();
	}
}
