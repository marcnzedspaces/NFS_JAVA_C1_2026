# Exercise 6 — Frontend API Observability

## Objective

Record API response details from the frontend browser console.

<!-- Time estimate removed -->

## Task

Create this file:

```text
src/services/apiClient.js
```



## Temporary Test Button

Add this temporarily in any React component:

```jsx
import { apiFetch } from "../services/apiClient";

function ObservabilityTestButton() {
  const testPing = async () => {
    const result = await apiFetch("/api/v1/observe/ping");
    console.log(result);
  };

  const testSlow = async () => {
    const result = await apiFetch("/api/v1/observe/slow");
    console.log(result);
  };

  const testFail = async () => {
    try {
      await apiFetch("/api/v1/observe/fail");
    } catch (error) {
      console.error("Handled frontend error:", error.message);
    }
  };

  return (
    <div>
      <button onClick={testPing}>Test Ping</button>
      <button onClick={testSlow}>Test Slow</button>
      <button onClick={testFail}>Test Fail</button>
    </div>
  );
}

export default ObservabilityTestButton;
```

## Expected Browser Console Output

```text
[API] GET /api/v1/observe/ping -> 200 (20ms)
[API] GET /api/v1/observe/slow -> 200 (1510ms)
[API ERROR] GET /api/v1/observe/fail failed after 35ms Demo failure for observability practice
```

## Submission

```markdown
## Exercise 6: Frontend API Observability

Frontend file created:

Endpoint tested:

Browser console output:

What is the difference between frontend observability and backend observability?
```

---
