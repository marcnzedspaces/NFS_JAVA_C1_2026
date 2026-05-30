# Exercise 5: Generate React Tests

## Objective

Generate and improve React Testing Library tests.

## Task

Use the React component from Exercise 4.

## Prompt to Use

```text
Generate React Testing Library tests for this component.

Requirements:
1. Use Vitest.
2. Test that the component renders correctly.
3. Test required field validation.
4. Test that onSubmit is called with correct data.
5. Do not use Enzyme.
6. Explain each test.
7. Do not assume props that are not shown.

Component:
[paste component here]
```

## Example Test

```jsx
import { render, screen } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import { describe, expect, it, vi } from "vitest";
import CourseForm from "./CourseForm";

describe("CourseForm", () => {
  it("shows an error when title is empty", async () => {
    const user = userEvent.setup();
    const handleSubmit = vi.fn();

    render(<CourseForm onSubmit={handleSubmit} />);

    await user.click(screen.getByRole("button", { name: /save course/i }));

    expect(screen.getByText("Title is required")).toBeInTheDocument();
    expect(handleSubmit).not.toHaveBeenCalled();
  });

  it("calls onSubmit with correct course data", async () => {
    const user = userEvent.setup();
    const handleSubmit = vi.fn();

    render(<CourseForm onSubmit={handleSubmit} />);

    await user.type(screen.getByPlaceholderText("Course title"), "Java Fullstack");
    await user.type(screen.getByPlaceholderText("Category"), "Programming");
    await user.type(screen.getByPlaceholderText("Duration"), "5 days");
    await user.click(screen.getByLabelText("Published"));

    await user.click(screen.getByRole("button", { name: /save course/i }));

    expect(handleSubmit).toHaveBeenCalledWith({
      title: "Java Fullstack",
      category: "Programming",
      duration: "5 days",
      published: true,
    });
  });
});
```

## Student Must Improve One Test

They must improve one AI-generated test by adding:

```
Better user interaction
Stronger assertion
Validation check
Negative case
Accessibility-friendly query
```

## Student Output

```markdown
## Exercise 5: React Test Improvement

AI-generated test:

Issue found:

My improved version:

Why the improved test is stronger:

Test result:
```
