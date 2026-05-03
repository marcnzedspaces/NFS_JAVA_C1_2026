const API_BASE_URL = "http://localhost:8080/api/v1";

export async function getCourses() {
    const response = await fetch(`${API_BASE_URL}/courses`);
    
    if (!response.ok) {
        throw new Error(`Failed to fetch courses: ${response.statusText}`);
    }
    
    return response.json();
}