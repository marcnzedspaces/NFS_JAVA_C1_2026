# Exercise 2: Backend Refactoring

## Objective

Refactor one Spring Boot method without changing its behaviour.

## Task

Refactor one method from:

```
CourseService.java
CourseController.java
AuthService.java
UserService.java
```

Recommended methods:

```
getCourseById()
updateCourse()
createCourse()
deleteCourse()
login()
```

## Sample Method for Students Who Need One

```java
public Course getCourseById(String id) {
    Optional<Course> course = courseRepository.findById(id);

    if (course.isPresent()) {
        return course.get();
    } else {
        throw new RuntimeException("Course not found");
    }
}
```

## Prompt to Use

```text
Refactor this Spring Boot method.

Rules:
1. Keep the same behaviour.
2. Do not add new features.
3. Do not change the database model.
4. Do not introduce new dependencies.
5. Improve readability and error handling.
6. Explain each change before showing the final code.

Code:
[paste your method here]
```

## Example Improved Version

```java
public Course getCourseById(String id) {
    return courseRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Course not found with ID: " + id));
}
```

## Student Must Test Using One of These

```
Postman
Browser
Frontend flow
Unit test
Existing smoke test
```

## Student Output

```markdown
## Exercise 2: Backend Refactoring

Original issue:

AI prompt used:

Original code summary:

Refactored code summary:

What changed:

Why it is better:

How I tested:

Did the behaviour change?
Yes / No

Evidence:
```
