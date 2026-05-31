# Exercise 3 — Improve Error Handling

## Objective

Return cleaner error responses instead of raw technical error messages.

<!-- Time estimate removed -->

## Task

Create an error response class and global exception handler.

Create this file:

```text
src/main/java/<your-package>/exception/ApiErrorResponse.java
```

## Code

```java
package com.example.fullstack.exception;

public class ApiErrorResponse {

    private int status;
    private String error;
    private String message;
    private String path;

    public ApiErrorResponse(int status, String error, String message, String path) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }
}
```

Create this file:

```text
src/main/java/<your-package>/exception/GlobalExceptionHandler.java
```

## Code

```java
package com.example.fullstack.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiErrorResponse> handleRuntimeException(
            RuntimeException exception,
            HttpServletRequest request
    ) {
        logger.warn("Runtime exception at {}: {}", request.getRequestURI(), exception.getMessage());

        ApiErrorResponse response = new ApiErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                exception.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiErrorResponse> handleAccessDeniedException(
            AccessDeniedException exception,
            HttpServletRequest request
    ) {
        logger.warn("Access denied at {}", request.getRequestURI());

        ApiErrorResponse response = new ApiErrorResponse(
                HttpStatus.FORBIDDEN.value(),
                "Forbidden",
                "You do not have permission to access this resource.",
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGeneralException(
            Exception exception,
            HttpServletRequest request
    ) {
        logger.error("Unexpected error at {}", request.getRequestURI(), exception);

        ApiErrorResponse response = new ApiErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                "Something went wrong. Please contact support.",
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
```

## Test

Call:

```text
http://localhost:8080/api/v1/observe/fail
```

## Expected JSON Style

```json
{
  "status": 400,
  "error": "Bad Request",
  "message": "Demo failure for observability practice",
  "path": "/api/v1/observe/fail"
}
```

## Submission

```markdown
## Exercise 3: Error Handling

File created:

Endpoint tested:

Before error response:

After error response:

Why is the new error response better?
```

---
