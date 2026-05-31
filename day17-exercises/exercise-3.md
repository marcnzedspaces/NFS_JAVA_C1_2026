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



Create this file:

```text
src/main/java/<your-package>/exception/GlobalExceptionHandler.java
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
