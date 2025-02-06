package com.sparta.ticketing.controller.healthcheck;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.TreeMap;

@RestController
public class HealthCheckController {

    @Value("${server.env}")
    private String env;

    @Value("${server.port}")
    private String port;

    @Value("${server.serverAddress}")
    private String serverAddress;

    @Value("${servername}")
    private String serverName;

    @GetMapping("/health")
    public ResponseEntity<?> healthCheck() {
        Map<String, String> responseData = new TreeMap<>();
        responseData.put("serverName", serverName);
        responseData.put("serverAddress", serverAddress);
        responseData.put("port", port);
        responseData.put("env", env);

        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/env")
    public ResponseEntity<String> getEnv() {
        return ResponseEntity.ok(env);
    }
}
