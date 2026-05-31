package com.example.demoapi.observability;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/observe")
public class ObservabilityDemoController {

    private static final Logger logger = LoggerFactory.getLogger(ObservabilityDemoController.class);

    @GetMapping("/ping")
    public Map<String, Object> ping() {
        logger.info("Observability ping endpoint called");

        return Map.of(
                "message", "Backend is running",
                "status", "OK"
        );
    }

    @GetMapping("/slow")
    public Map<String, Object> slowEndpoint() throws InterruptedException {
        logger.info("Slow endpoint called. Simulating delay...");

        Thread.sleep(1500);

        logger.info("Slow endpoint completed");

        return Map.of(
                "message", "Slow endpoint completed",
                "delay", "1500ms"
        );
    }

    @GetMapping("/fail")
    public Map<String, Object> failEndpoint() {
        logger.warn("Fail endpoint called. Throwing demo exception.");

        throw new RuntimeException("Demo failure for observability practice");
    }

    @GetMapping("/fail-runtime")
    public Map<String, Object> failRuntime() {
        logger.warn("Runtime fail endpoint called. ");

        throw new RuntimeException("Demo failure for observability practice");
    }

    @GetMapping("/fail-general")
    public Map<String, Object> failGeneral() {
        logger.warn("General fail endpoint called. Throwing demo exception.");

        throw new RuntimeException("Demo failure for errror handling practice");
    }
}