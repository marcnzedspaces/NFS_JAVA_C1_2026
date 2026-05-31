# Exercise 5 — Check Query Performance with explain()

## Objective

Use MongoDB `explain()` to understand query behaviour.

<!-- Time estimate removed -->

## Task

Run this in `mongosh`:

```javascript
db.courses.find({ category: "Programming" }).explain("executionStats")
```

Then run:

```javascript
db.courses.find({ category: "Programming", published: true }).explain("executionStats")
```

## Look For

```text
executionTimeMillis
totalDocsExamined
totalKeysExamined
```

## Submission

```markdown
## Exercise 5: MongoDB Query Performance

Query tested:

executionTimeMillis:

totalDocsExamined:

totalKeysExamined:

Was an index used?

What does this tell you?
```

---
