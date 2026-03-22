package com.unknown.gateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class ServicePreFilter implements WebFilter {
    static {
        log.debug("ServicePreFilter");
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        log.trace("Service Pre Filter executed");
        String path = exchange.getRequest().getURI().getPath().replaceFirst("/service", "");
        log.debug("Output path: {}", path);
        return chain.filter(exchange.mutate().request(exchange.getRequest().mutate().path(path).build()).build());
    }
}
