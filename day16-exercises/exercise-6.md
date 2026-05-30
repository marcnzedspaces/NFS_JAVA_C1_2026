# Exercise 6: Git Diff and AI Review

## Objective

Use Git diff to understand exactly what changed.

## Task

Run:

```bash
git diff
```

Or for staged files:

```bash
git diff --staged
```

Then copy the diff into AI.

## Prompt to Use

```text
Review this git diff as if you are reviewing a pull request.

Focus on:
1. what changed
2. whether behaviour changed
3. whether the refactoring is safe
4. what tests should be run
5. possible risks

Diff:
[paste git diff here]
```

## Student Output

```markdown
## Exercise 6: Git Diff Review

Files changed:

Summary of changes:

Possible risk:

How I tested:

Would I commit this change?
Yes / No

Reason:
```
