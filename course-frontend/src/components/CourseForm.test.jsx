import { describe, expect, it, vi } from "vitest";
import { render, screen } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import { MemoryRouter } from "react-router-dom";

import CourseForm from "./CourseForm";

function renderCourseForm(props = {}) {
  const defaultProps = {
    initialData: null,
    onSubmit: vi.fn(),
    buttonText: "Create Course",
  };

  return render(
    <MemoryRouter>
      <CourseForm {...defaultProps} {...props} />
    </MemoryRouter>
  );
}

describe("CourseForm", () => {
  it("shows validation errors when submitting an empty form", async () => {
    const user = userEvent.setup();
    const mockSubmit = vi.fn();

    renderCourseForm({ onSubmit: mockSubmit });

    await user.click(
      screen.getByRole("button", { name: /create course/i })
    );

    expect(screen.getByText(/title is required/i)).toBeInTheDocument();
    expect(screen.getByText(/category is required/i)).toBeInTheDocument();
    expect(screen.getByText(/duration is required/i)).toBeInTheDocument();

    expect(mockSubmit).not.toHaveBeenCalled();
  });

  it("submits valid course data", async () => {
    const user = userEvent.setup();
    const mockSubmit = vi.fn();

    renderCourseForm({ onSubmit: mockSubmit });

    await user.type(
      screen.getByLabelText(/course title/i),
      "React Testing"
    );

    await user.type(
      screen.getByLabelText(/category/i),
      "Frontend"
    );

    await user.type(
      screen.getByLabelText(/duration/i),
      "2"
    );

    await user.click(screen.getByLabelText(/published/i));

    await user.click(
      screen.getByRole("button", { name: /create course/i })
    );

    expect(mockSubmit).toHaveBeenCalledWith({
      title: "React Testing",
      category: "Frontend",
      duration: 2,
      published: true,
    });
  });
});