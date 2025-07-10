package com.unknown.gateway.configs;

import com.unknown.gateway.properties.RoutesSettings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableConfigurationProperties(RoutesSettings.class)
public class AuthRoutes {
    @Value("${spring.security.keycloak.realm}")
    private String realm;

    static {
        log.debug("PostRoutes initialized");
    }

    @Bean
    public RouteLocator authsRoutes(RouteLocatorBuilder builder, RoutesSettings settings) {
        log.debug("Auth routes: {}", settings.getAuth().stream().map(RoutesSettings.Service::getUri).toList());
        RouteLocatorBuilder.Builder rb = builder.routes();
        settings.getAuth().forEach(service -> rb
                .route(
                    p -> p.path("/auth/login")
                            .filters(f -> f.setPath("/realms/%s/protocol/openid-connect/token".formatted(realm)))
                            .uri(service.getUri())
                ).route(
                        p -> p.path("/auth/signup")
                            .filters(f -> f.setPath("/realms/%s/account".formatted(realm)))
                            .uri(service.getUri())
                ).route(
                        p -> p.path("/auth/logout")
                            .filters(f -> f.setPath("/realms/%s/protocol/openid-connect/logout".formatted(realm)))
                            .uri(service.getUri())
        ));
        return rb.build();
    }
}
