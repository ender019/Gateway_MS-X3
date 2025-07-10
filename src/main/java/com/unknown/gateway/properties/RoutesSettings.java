package com.unknown.gateway.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ConfigurationProperties(prefix = "routes")
public class RoutesSettings {
    private List<ServiceWrapper> user;
    private List<ServiceWrapper> post;
    private List<ServiceWrapper> auth;

    @Getter
    @Setter
    public static class ServiceWrapper {
        private Service service;
    }

    @Getter
    @Setter
    public static class Service {
        private String uri;
    }

    public List<Service> getUser() {
        return user.stream()
                .map(ServiceWrapper::getService)
                .collect(Collectors.toList());
    }

    public List<Service> getPost() {
        return post.stream()
                .map(ServiceWrapper::getService)
                .collect(Collectors.toList());
    }

    public List<Service> getAuth() {
        return auth.stream()
                .map(ServiceWrapper::getService)
                .collect(Collectors.toList());
    }
}
