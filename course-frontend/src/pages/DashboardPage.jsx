import { useEffect, useState } from "react";
import { getAllCourses } from "../services/courseApi";

function DashboardPage() {
  const [courses, setCourses] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    async function loadDashboardData() {
      try {
        const data = await getAllCourses();
        setCourses(data);
      } catch (error) {
        console.error(error);
        setError("Could not load dashboard data.");
      } finally {
        setLoading(false);
      }
    }

    loadDashboardData();
  }, []);

  if (loading) {
    return <p>Loading dashboard...</p>;
  }

  if (error) {
    return <p className="error-message">{error}</p>;
  }

  const totalCourses = courses.length;
  const publishedCourses = courses.filter((course) => course.published).length;
  const draftCourses = courses.filter((course) => !course.published).length;

  return (
    <section>
      <h1>Dashboard</h1>

      <p>
        This is a protected page. You can only see this page after logging in.
      </p>

      <div className="card-grid">
        <div className="card">
          <h2>Total Courses</h2>
          <p className="metric">{totalCourses}</p>
        </div>

        <div className="card">
          <h2>Published Courses</h2>
          <p className="metric">{publishedCourses}</p>
        </div>

        <div className="card">
          <h2>Draft Courses</h2>
          <p className="metric">{draftCourses}</p>
        </div>
      </div>
    </section>
  );
}

export default DashboardPage;