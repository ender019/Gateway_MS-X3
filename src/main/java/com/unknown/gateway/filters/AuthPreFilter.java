package com.unknown.gateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthPreFilter{

    static {
        log.info("AuthPreFilter");
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        log.info("securityFilterChain executed");
        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .oauth2Login(Customizer.withDefaults());

        return http.authorizeExchange(c -> c
                        .pathMatchers("/error").permitAll()
                        .pathMatchers("/auth/**").permitAll()
                        .pathMatchers("/auth/logout").authenticated()
                        .anyExchange().authenticated()
                ).build();
    }
}
