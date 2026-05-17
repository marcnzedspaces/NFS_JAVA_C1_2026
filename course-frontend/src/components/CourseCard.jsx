import { Link } from "react-router-dom";

function CourseCard({ course, isAdmin, onDelete }) {
  return (
    <div className="card">
      <h2>{course.title}</h2>

      <p>
        <strong>Category:</strong> {course.category}
      </p>

      <p>
        <strong>Duration:</strong> {course.duration} hours
      </p>

      <p>
        <strong>Status:</strong> {course.published ? "Published" : "Draft"}
      </p>

      <div className="card-actions">
        <Link to={`/courses/${course.id}`}>View Details</Link>

        {isAdmin && (
          <>
            <Link to={`/courses/${course.id}/edit`}>Edit</Link>

            <button
              type="button"
              className="danger-button"
              onClick={() => onDelete(course)}
            >
              Delete
            </button>
          </>
        )}
      </div>
    </div>
  );
}

export default CourseCard;