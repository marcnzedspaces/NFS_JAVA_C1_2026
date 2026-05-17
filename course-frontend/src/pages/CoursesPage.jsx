import { useEffect, useMemo, useState } from "react";
import { Link } from "react-router-dom";

import CourseCard from "../components/CourseCard";
import Pagination from "../components/Pagination";
import SearchBox from "../components/SearchBox";

import { deleteCourse, getAllCourses } from "../services/courseApi";

function CoursesPage() {
  const [courses, setCourses] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const [successMessage, setSuccessMessage] = useState("");

  const [searchTerm, setSearchTerm] = useState("");
  const [currentPage, setCurrentPage] = useState(1);
  const [pageSize, setPageSize] = useState(5);

  const role = localStorage.getItem("role");
  const isAdmin = role === "ADMIN";

  useEffect(() => {
    loadCourses();
  }, []);

  async function loadCourses() {
    setLoading(true);
    setError("");

    try {
      const data = await getAllCourses();
      setCourses(data);
    } catch (error) {
      console.error(error);
      setError("Could not load courses.");
    } finally {
      setLoading(false);
    }
  }

  const filteredCourses = useMemo(() => {
    const keyword = searchTerm.toLowerCase().trim();

    if (!keyword) {
      return courses;
    }

    return courses.filter((course) => {
      const title = course.title.toLowerCase();
      const category = course.category.toLowerCase();
      const status = course.published ? "published" : "draft";

      return (
        title.includes(keyword) ||
        category.includes(keyword) ||
        status.includes(keyword)
      );
    });
  }, [courses, searchTerm]);

  const totalPages = Math.ceil(filteredCourses.length / pageSize) || 1;

  const paginatedCourses = useMemo(() => {
    const startIndex = (currentPage - 1) * pageSize;
    const endIndex = startIndex + pageSize;

    return filteredCourses.slice(startIndex, endIndex);
  }, [filteredCourses, currentPage, pageSize]);

  useEffect(() => {
    setCurrentPage(1);
  }, [searchTerm, pageSize]);

  useEffect(() => {
    if (currentPage > totalPages) {
      setCurrentPage(totalPages);
    }
  }, [currentPage, totalPages]);

  async function handleDeleteCourse(course) {
    const confirmDelete = window.confirm(
      `Are you sure you want to delete "${course.title}"?`
    );

    if (!confirmDelete) {
      return;
    }

    setError("");
    setSuccessMessage("");

    try {
      await deleteCourse(course.id);

      setCourses((currentCourses) =>
        currentCourses.filter((item) => item.id !== course.id)
      );

      setSuccessMessage(`"${course.title}" was deleted successfully.`);
    } catch (error) {
      console.error(error);
      setError("Could not delete course.");
    }
  }

  if (loading) {
    return <p>Loading courses...</p>;
  }

  if (error && courses.length === 0) {
    return <p className="error-message">{error}</p>;
  }

  return (
    <section>
      <div className="page-header">
        <div>
          <h1>Courses</h1>
          <p>Search, view, edit, and manage courses from the backend.</p>
        </div>

        {isAdmin && (
          <Link to="/courses/create">
            <button>Create Course</button>
          </Link>
        )}
      </div>

      {successMessage && (
        <p className="success-message">{successMessage}</p>
      )}

      {error && <p className="error-message">{error}</p>}

      <SearchBox
        searchTerm={searchTerm}
        onSearchChange={setSearchTerm}
        resultCount={filteredCourses.length}
        totalCount={courses.length}
      />

      {filteredCourses.length === 0 ? (
        <p>No courses match your search.</p>
      ) : (
        <>
          <div className="card-grid">
            {paginatedCourses.map((course) => (
              <CourseCard
                key={course.id}
                course={course}
                isAdmin={isAdmin}
                onDelete={handleDeleteCourse}
              />
            ))}
          </div>

          <Pagination
            currentPage={currentPage}
            totalPages={totalPages}
            pageSize={pageSize}
            onPageChange={setCurrentPage}
            onPageSizeChange={setPageSize}
          />
        </>
      )}
    </section>
  );
}

export default CoursesPage;