const COURSE_API_URL = "http://localhost:8080/api/v1/courses";

function getAuthHeaders() {
  const token = localStorage.getItem("token");

  return {
    "Content-Type": "application/json",
    Authorization: `Bearer ${token}`,
  };
}

export async function getAllCourses() {
  const response = await fetch(COURSE_API_URL, {
    headers: getAuthHeaders(),
  });

  if (!response.ok) {
    throw new Error("Failed to load courses");
  }

  return response.json();
}

export async function getCourseById(id) {
  const response = await fetch(`${COURSE_API_URL}/${id}`, {
    headers: getAuthHeaders(),
  });

  if (!response.ok) {
    throw new Error("Failed to load course");
  }

  return response.json();
}