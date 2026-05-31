# Exercise 7 — Security Hardening Checklist

## Objective

Review the application for basic security risks.

<!-- Time estimate removed -->

## Task

Review your project and complete the checklist.

## Backend Checklist

| Check                                           | Pass / Fail / Not Sure | Notes |
| ----------------------------------------------- | ---------------------- | ----- |
| Protected endpoints require authentication      |                        |       |
| Admin-only actions are checked in backend       |                        |       |
| Passwords are hashed                            |                        |       |
| JWT secret is not hardcoded in public code      |                        |       |
| Error messages do not expose sensitive details  |                        |       |
| Logs do not print passwords or tokens           |                        |       |
| CORS is not open to every origin for production |                        |       |
| Input validation exists for create/update forms |                        |       |

## Frontend Checklist

| Check                                          | Pass / Fail / Not Sure | Notes |
| ---------------------------------------------- | ---------------------- | ----- |
| Logout clears token                            |                        |       |
| Protected pages redirect unauthenticated users |                        |       |
| Admin buttons are hidden from normal users     |                        |       |
| Backend still enforces permissions             |                        |       |
| Errors are shown clearly to users              |                        |       |
| No secrets are stored in frontend source code  |                        |       |

## Useful Search Commands

Mac/Linux:

```bash
grep -R "System.out.println" src/main/java
grep -R "password" src/main/java
grep -R "secret" src/main/resources
grep -R "allowedOrigins" src/main/java
```

Windows PowerShell:

```powershell
Select-String -Path "src/main/java/**/*.java" -Pattern "System.out.println"
Select-String -Path "src/main/java/**/*.java" -Pattern "password"
Select-String -Path "src/main/resources/*" -Pattern "secret"
Select-String -Path "src/main/java/**/*.java" -Pattern "allowedOrigins"
```

## Submission

```markdown
## Exercise 7: Security Hardening Checklist

Backend checklist completed:
Yes / No

Frontend checklist completed:
Yes / No

One risk found:

Recommended fix:

One security rule I learned:
```

---
