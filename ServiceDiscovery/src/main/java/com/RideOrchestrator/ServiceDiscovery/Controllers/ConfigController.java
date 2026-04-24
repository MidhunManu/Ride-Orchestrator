package com.RideOrchestrator.ServiceDiscovery.Controllers;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RideOrchestrator.ServiceDiscovery.Services.ConfigService;

@RestController
@CrossOrigin(origins = "*")
public class ConfigController {
    private final ConfigService configService;

    public ConfigController(ConfigService configService) {
        this.configService = configService;
    }

    @GetMapping(value = "service-mapping")
    public List<ConfigService.ServiceInfo> showServiceMapping() {
        return configService.getServices();
    }

    @PostMapping(value = "service-lookup/{name}")
    public ResponseEntity<ConfigService.ServiceInfo> serviceLookUp(@PathVariable String name) {
        ConfigService.ServiceInfo service = configService.find(name);

        if (service == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(service);
    }

    @GetMapping(value = "users")
    public String users() {
        return "users";
    }
}
