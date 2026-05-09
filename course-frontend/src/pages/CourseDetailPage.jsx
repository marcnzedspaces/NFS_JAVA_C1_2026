import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import { getCourseById } from "../services/courseApi";

function CourseDetailPage() {
  const { id } = useParams();

  const [course, setCourse] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    async function loadCourse() {
      try {
        const data = await getCourseById(id);
        setCourse(data);
      } catch (error) {
        console.error(error);
        setError("Course not found or could not be loaded.");
      } finally {
        setLoading(false);
      }
    }

    loadCourse();
  }, [id]);

  if (loading) {
    return <p>Loading course details...</p>;
  }

  if (error) {
    return (
      <section>
        <h1>Course Not Found</h1>
        <p className="error-message">{error}</p>
        <Link to="/courses">Back to Courses</Link>
      </section>
    );
  }

  return (
    <section>
      <h1>{course.title}</h1>

      <div className="card">
        <p>
          <strong>ID:</strong> {course.id}
        </p>

        <p>
          <strong>Category:</strong> {course.category}
        </p>

        <p>
          <strong>Duration:</strong> {course.duration} hours
        </p>

        <p>
          <strong>Status:</strong> {course.published ? "Published" : "Draft"}
        </p>
      </div>

      <div className="page-actions">
        <Link to="/courses">Back to Courses</Link>
      </div>
    </section>
  );
}

export default CourseDetailPage;