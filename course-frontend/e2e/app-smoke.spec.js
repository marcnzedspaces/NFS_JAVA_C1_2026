import { test, expect } from "@playwright/test";

test("admin can login and view courses page", async ({ page }) => {
  await page.goto("/login");

  await page.getByPlaceholder("Enter email").fill("admin@admin.com");
  await page.getByPlaceholder("Enter password").fill("pwd12345");

  await page.getByRole("button", { name: /login/i }).click();

  await expect(page).toHaveURL(/dashboard/);

  await page.goto("/courses");

  await expect(
    page.getByRole("heading", { name: /courses/i })
  ).toBeVisible();

  await expect(
    page.getByText(/search, view, edit, and manage courses/i)
  ).toBeVisible();
});

test("admin can search courses", async ({ page }) => {
  await page.goto("/login");

  await page.getByPlaceholder("Enter email").fill("admin@admin.com");
  await page.getByPlaceholder("Enter password").fill("pwd12345");

  await page.getByRole("button", { name: /login/i }).click();

  await page.goto("/courses");

  await page.getByPlaceholder(/search/i).fill("JWT");

  await expect(page.getByText(/showing/i)).toBeVisible();
});