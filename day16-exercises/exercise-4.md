# Exercise 4: React Component Refactoring

## Objective

Refactor a React component for readability and maintainability.

## Task

Choose one component:

```
CourseForm.jsx
CourseList.jsx
CourseCard.jsx
Login.jsx
Dashboard.jsx
```

Recommended: `CourseForm.jsx`

## Sample Starting Code

```jsx
import { useState } from "react";

function CourseForm({ initialData, onSubmit }) {
  const [title, setTitle] = useState(initialData?.title || "");
  const [category, setCategory] = useState(initialData?.category || "");
  const [duration, setDuration] = useState(initialData?.duration || "");
  const [published, setPublished] = useState(initialData?.published || false);
  const [error, setError] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!title) {
      setError("Title is required");
      return;
    }

    onSubmit({
      title,
      category,
      duration,
      published,
    });
  };

  return (
    <form onSubmit={handleSubmit}>
      {error && <p>{error}</p>}

      <input
        type="text"
        value={title}
        onChange={(e) => setTitle(e.target.value)}
        placeholder="Course title"
      />

      <input
        type="text"
        value={category}
        onChange={(e) => setCategory(e.target.value)}
        placeholder="Category"
      />

      <input
        type="text"
        value={duration}
        onChange={(e) => setDuration(e.target.value)}
        placeholder="Duration"
      />

      <label>
        <input
          type="checkbox"
          checked={published}
          onChange={(e) => setPublished(e.target.checked)}
        />
        Published
      </label>

      <button type="submit">Save Course</button>
    </form>
  );
}

export default CourseForm;
```

## Prompt to Use

```text
Review this React component.

Task:
1. Reduce repeated state handling.
2. Improve readability.
3. Keep the same UI and behaviour.
4. Do not introduce a new form library.
5. Do not change the component props.
6. Explain the changes before showing code.

Code:
[paste component here]
```

## Expected Refactoring Direction

Students may refactor from multiple `useState` calls to one `formData` object.

Example:

```jsx
const [formData, setFormData] = useState({
  title: initialData?.title || "",
  category: initialData?.category || "",
  duration: initialData?.duration || "",
  published: initialData?.published || false,
});
```

Example reusable handler:

```jsx
const handleChange = (e) => {
  const { name, value, type, checked } = e.target;

  setFormData((previousData) => ({
    ...previousData,
    [name]: type === "checkbox" ? checked : value,
  }));
};
```

## Student Output

```markdown
## Exercise 4: React Refactoring

Component refactored:

Original issue:

AI suggestion accepted:

AI suggestion rejected:

What changed:

How I tested the form:

Screenshot or explanation:
```
