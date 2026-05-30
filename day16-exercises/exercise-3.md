# Exercise 3: Generate and Improve Backend Tests

## Objective

Use AI to generate test cases, then improve them manually.

## Task

Use the backend method from Exercise 2.

Ask AI to generate tests.

## Prompt to Use

```text
Generate unit tests for this Spring Boot service method.

Requirements:
1. Use JUnit 5.
2. Use Mockito if repository mocking is required.
3. Include a successful case.
4. Include a failure case.
5. Include an edge case.
6. Explain what each test verifies.
7. Do not assume fields or classes that are not shown.

Method:
[paste method here]

Related model fields:
[paste important fields only]
```

## Example Test Case

```java
@Test
void getCourseById_shouldReturnCourseWhenFound() {
    Course course = new Course();
    course.setId("101");
    course.setTitle("Fullstack Java");

    when(courseRepository.findById("101")).thenReturn(Optional.of(course));

    Course result = courseService.getCourseById("101");

    assertEquals("101", result.getId());
    assertEquals("Fullstack Java", result.getTitle());

    verify(courseRepository).findById("101");
}
```

## Failure Test Example

```java
@Test
void getCourseById_shouldThrowExceptionWhenCourseNotFound() {
    when(courseRepository.findById("999")).thenReturn(Optional.empty());

    RuntimeException exception = assertThrows(RuntimeException.class, () -> {
        courseService.getCourseById("999");
    });

    assertEquals("Course not found with ID: 999", exception.getMessage());

    verify(courseRepository).findById("999");
}
```

## Manual Improvement Requirement

Students must improve at least **one AI-generated test**.

Examples of improvement:

```
Add stronger assertions
Check exception message
Verify repository method was called
Add negative test case
Remove unnecessary test
Fix wrong assumptions made by AI
```

## Student Output

```markdown
## Exercise 3: Backend Test Improvement

AI-generated test case:

Problem with AI-generated test:

My improvement:

Why my version is better:

Test result:
Pass / Fail
```
