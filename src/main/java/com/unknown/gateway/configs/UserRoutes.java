package com.unknown.gateway.configs;

import com.unknown.gateway.properties.RoutesSettings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableConfigurationProperties(RoutesSettings.class)
public class UserRoutes {
    static {
        log.debug("UserRoutes initialized");
    }

    @Bean
    public RouteLocator usersRoutes(RouteLocatorBuilder builder, RoutesSettings settings) {
        log.debug("User routes: {}", settings.getUser().stream().map(RoutesSettings.Service::getUri).toList());
        RouteLocatorBuilder.Builder rb = builder.routes();
        settings.getUser().forEach(service -> rb
                .route( p -> p.path("/user/**").uri(service.getUri()) )
                .route( p -> p.path("/subscribe/**").uri(service.getUri()) )
        );
        return rb.build();
    }
}
