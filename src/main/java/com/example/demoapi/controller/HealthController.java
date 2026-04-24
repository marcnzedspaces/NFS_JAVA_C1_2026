package com.example.demoapi.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

/*************  ✨ Windsurf Command ⭐  *************/
    /**
     * Get the health status of the API.
     *
     * @return a map with "status" and "message" keys, indicating the health status of the API.
     */
/*******  21500c81-b9ef-4446-a6b6-032d1b1deacd  *******/        
    @GetMapping("/api/health")
    public Map<String, String> health() {
        return Map.of(
            "status", "ok",
            "message", "Demo API is healthy"
        );
    }
}
