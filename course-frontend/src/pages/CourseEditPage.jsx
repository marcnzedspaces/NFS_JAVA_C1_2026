import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import CourseForm from "../components/CourseForm";
import { getCourseById, updateCourse } from "../services/courseApi";

function CourseEditPage() {
    const { id } = useParams();
    const navigate = useNavigate();

    const [course, setCourse] = useState(null);
    const [loading, setLoading] = useState(true);
    const [message, setMessage] = useState("");
    const [error, setError] = useState("");

    useEffect(() => {
        async function loadCourse() {
            try {
                const data = await getCourseById(id);
                setCourse(data);
            } catch (error) {
                console.error(error);
                setError(`Course not found with ID: ${id}`);
            } finally {
                setLoading(false);
            }
        }

        loadCourse();
    }, [id]);

    async function handleUpdateCourse(courseData) {
        setMessage("");
        setError("");

        try {
            await updateCourse(id, courseData);

            setMessage("Course updated successfully.");

            setTimeout(() => {
                navigate("/courses");
            }, 700);
        } catch (error) {
            console.error(error);
            setError("Could not update course. Please check your input.");
        }
    }

    if (loading) {
        return <p>Loading course...</p>;
    }

    if (error && !course) {
        return (
            <section>
                <h1>Course Not Found</h1>
                <p className="error-message">{error}</p>
                <button onClick={() => navigate("/courses")}>Back to Courses</button>
            </section>
        );
    }

    return (
        <section>
            <div className="page-header">
                <div>
                    <h1>Edit Course</h1>
                    <p>Update the selected course.</p>
                </div>
            </div>

            {message && <p className="success-message">{message}</p>}
            {error && <p className="error-message">{error}</p>}

            <CourseForm
                initialData={course}
                onSubmit={handleUpdateCourse}
                buttonText="Update Course"
            />
        </section>
    );
}

export default CourseEditPage;