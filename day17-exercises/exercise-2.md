# Exercise 2 — Create Observability Demo Endpoints

## Objective

Create demo endpoints to observe a normal request, a slow request, and a failed request.

<!-- Time estimate removed -->

## Task

Create this file:

```text
src/main/java/<your-package>/observability/ObservabilityDemoController.java
```

## Code

```java
package com.example.fullstack.observability;

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
        logger.info("Ping endpoint called");

        return Map.of(
                "message", "Backend is running",
                "status", "OK"
        );
    }

    @GetMapping("/slow")
    public Map<String, Object> slowEndpoint() throws InterruptedException {
        logger.info("Slow endpoint called");

        Thread.sleep(1500);

        logger.info("Slow endpoint completed");

        return Map.of(
                "message", "Slow endpoint completed",
                "delay", "1500ms"
        );
    }

    @GetMapping("/fail")
    public Map<String, Object> failEndpoint() {
        logger.warn("Fail endpoint called");

        throw new RuntimeException("Demo failure for observability practice");
    }
}
```

## Test URLs

```text
http://localhost:8080/api/v1/observe/ping
http://localhost:8080/api/v1/observe/slow
http://localhost:8080/api/v1/observe/fail
```

## Expected Result

| Endpoint |      Expected Status | Purpose                   |
| -------- | -------------------: | ------------------------- |
| `/ping`  |                  200 | Normal successful request |
| `/slow`  |                  200 | Slow request simulation   |
| `/fail`  | 500 or handled error | Failure simulation        |

## Submission

```markdown
## Exercise 2: Observability Demo Endpoints

Ping result:

Slow endpoint duration:

Fail endpoint result:

What did you learn from the logs?
```

---
