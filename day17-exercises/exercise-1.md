# Exercise 1 — Add Backend Request Logging

## Objective

Add backend logs so that every API request can be observed from the terminal.

<!-- Time estimate removed -->

## Task

Create a request logging filter in your Spring Boot backend.

Create this file:

```text
src/main/java/<your-package>/observability/RequestLoggingFilter.java
```

Replace `<your-package>` with your actual package name.

Example:

```java
package com.example.fullstack.observability;
```

## Code

```java
package com.example.fullstack.observability;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class RequestLoggingFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        long startTime = System.currentTimeMillis();

        try {
            filterChain.doFilter(request, response);
        } finally {
            long duration = System.currentTimeMillis() - startTime;

            logger.info(
                    "HTTP {} {} -> status={} duration={}ms",
                    request.getMethod(),
                    request.getRequestURI(),
                    response.getStatus(),
                    duration
            );
        }
    }
}
```

## Test

Start the backend:

```bash
mvn spring-boot:run
```

Call any API endpoint from the browser, frontend, or Postman.

Example:

```text
GET http://localhost:8080/api/v1/courses
```

## Expected Output

In the backend terminal, you should see something like:

```text
HTTP GET /api/v1/courses -> status=200 duration=35ms
```

## Submission

```markdown
## Exercise 1: Backend Request Logging

File created:

Endpoint tested:

Log output copied from terminal:

What does the log tell you?
```

---
