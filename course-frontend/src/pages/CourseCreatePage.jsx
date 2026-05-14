import { useState } from "react";
import { useNavigate } from "react-router-dom";
import CourseForm from "../components/CourseForm";
import { createCourse } from "../services/courseApi";

function CourseCreatePage() {
    const navigate = useNavigate();

    const [message, setMessage] = useState("");
    const [error, setError] = useState("");

    async function handleCreateCourse(courseData) {
        setMessage("");
        setError("");

        try {
            await createCourse(courseData);

            setMessage("Course created successfully.");

            setTimeout(() => {
                navigate("/courses");
            }, 700);
        } catch (error) {
            console.error(error);
            setError("Could not create course. Please check your input.");
        }
    }

    return (
        <section>
            <div className="page-header">
                <div>
                    <h1>Create Course</h1>
                    <p>Add a new course to the database.</p>
                </div>
            </div>

            {message && <p className="success-message">{message}</p>}
            {error && <p className="error-message">{error}</p>}

            <CourseForm
                initialData={null}
                onSubmit={handleCreateCourse}
                buttonText="Create Course"
            />
        </section>
    );
}

export default CourseCreatePage;