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
public class PostRoutes {
    static {
        log.debug("PostRoutes initialized");
    }

    @Bean
    public RouteLocator postsRoutes(RouteLocatorBuilder builder, RoutesSettings settings) {
        log.debug("Post routes: {}", settings.getPost().stream().map(RoutesSettings.Service::getUri).toList());
        RouteLocatorBuilder.Builder rb = builder.routes();
        settings.getPost().forEach(service -> rb.route( p -> p.path("/post/**").uri(service.getUri()) ));
        return rb.build();
    }
}
