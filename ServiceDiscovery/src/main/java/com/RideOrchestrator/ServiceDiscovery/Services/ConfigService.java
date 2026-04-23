package com.RideOrchestrator.ServiceDiscovery.Services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
@ConfigurationProperties(prefix = "discovery")
public class ConfigService {
    private List<ServiceInfo> services;
    private Map<String, ServiceInfo> cache;

    @PostConstruct
    public void init() {
        if (services != null) {
            this.cache = services.stream()
                         .collect(Collectors.toMap(
                            ServiceInfo::getName,
                            s -> s,
                            (existing, replacement) -> existing
                         ));
        }
    }

    public ServiceInfo find(String name) {
        return cache != null ? cache.get(name) : null;
    }

    public List<ServiceInfo> getServices() { 
        System.out.println(services);
        return services;
    }
    public void setServices(List<ServiceInfo> services) { this.services = services; }

    public static class ServiceInfo {
        private String name;    
        private String host;
        private int port;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getHost() { return host; }
        public void setHost(String host) { this.host = host; }
        public int getPort() { return port; }
        public void setPort(int port) { this.port = port; }
    }
}
